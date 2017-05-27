/** GenoMetric Space Explorer (GeMSE) Copyright (C) 2017 Vahid Jalili
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package GeMSE.IO;

import GeMSE.GS.SampleData;
import GeMSE.GS.SampleData.Feature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Francesco Venco
 */
public class GenometricMapper {

    private final boolean DEBUG = false;
    private String currentChrom;
    private Set<String> referenceAttributeNames;

    /**
     * Possible operations for Map.
     */
    public static enum MapOp {

        /**
         * Count the number of intersecting regions
         */
        Count,
        /**
         * Set a flag to true if at lest one region intersects with the
         * reference. False otherwise
         */
        Exist,
        /**
         * Set a flag to false if at lest one region intersects with the
         * reference. true otherwise
         */
        NotExist,
        /**
         * Take the minimum value of a specific attribute from all the regions
         * intersecting with the reference. Valid only for numeric values.
         */
        Min,
        /**
         * Take the maximum value of a specific attribute from all the regions
         * intersecting with the reference. Valid only for numeric values.
         */
        Max,
        /**
         * Take the average value of a specific attribute from all the regions
         * intersecting with the reference. Valid only for numeric values.
         */
        Average,
        /**
         * Sum the values of a specific attribute from all the regions
         * intersecting with the reference. Valid only for numeric values.
         */
        Sum,
        /**
         * Standard deviation
         */
        StandardDeviation,
        /**
         * Median value
         */
        Median;
        /**
         * Concatenate as strings all the values of a specific attribute from
         * all the regions intersecting with reference.
         */
        //BAG;
    }
    //results structures
    private SampleData results;
    //private Feature out_region;
    //list of operations( the can be many) and relative ids
    private List<MapOp> operations;
    private List<String> valueIndices;

    public GenometricMapper(MapOp operation, String valueIndex) {
        operations = new ArrayList<>();
        valueIndices = new ArrayList<>();
        this.addOperation(operation, valueIndex);

    }

    public GenometricMapper(List<MapOp> operations, List<String> valueIndices) {
        this.operations = operations;
        this.valueIndices = valueIndices;
    }

    public GenometricMapper() {
        operations = new ArrayList<>();
        valueIndices = new ArrayList<>();
    }

    public final void addOperation(MapOp operation, String valueIndex) {
        operations.add(operation);
        valueIndices.add(valueIndex);
    }

    /**
     * Function that scans two sets of regions: a reference and an
     * "experimental" set, and returns the mapped regions Both the collections
     * of regions are first ordered in order for the algorithm to work!
     *
     */
    public void scan(SampleData ref_regions, SampleData exp_regions, int offset_l, int offset_r) {
        scan(ref_regions, exp_regions, offset_l, offset_r, false);
    }

    /**
     * Function that scans two sets of regions: a reference and an
     * "experimental" set, and returns the mapped regions Both the collections
     * of regions are first ordered in order for the algorithm to work!
     *
     */
    public void scan(SampleData ref_regions, SampleData exp_regions,
            int offset_l, int offset_r, boolean stranded) {


        //init results
        results = new SampleData();
        
        results.chrCount = ref_regions.chrCount;
        results.featuresCount = ref_regions.featuresCount;
        results.fileName = exp_regions.fileName;
        results.fullPath = exp_regions.fullPath;
        results.messages = exp_regions.messages;
        results.metaData = exp_regions.metaData;
        results.sampleID = exp_regions.sampleID;
        results.determinedFeatures = ref_regions.determinedFeatures;
        
        
        Set<String> chromosomes = ref_regions.features.keySet();
        referenceAttributeNames = ref_regions.GetNumAttributes();
        FeatureComparator fc = new FeatureComparator();
        for (String chr : chromosomes) {
            if(!results.features.containsKey(chr))
                results.features.put(chr, new ArrayList<Feature>());
            
            currentChrom = chr;
            ArrayList<Feature> chr_ref_regions = ref_regions.features.get(chr);
            ArrayList<Feature> chr_exp_regions = exp_regions.features.get(chr);
            //sorting
            Collections.sort(chr_ref_regions, fc);
            if(chr_exp_regions!= null)
                Collections.sort(chr_exp_regions, fc);
            
            scan(chr_ref_regions, chr_exp_regions, 0, 0, stranded);
        }
    }

    /**
     * Chromosome by chromosome scan ( each list is considered a separated
     * chromosome!)
     */
    private void scan(ArrayList<Feature> ref_regions, ArrayList<Feature> exp_regions,
            int offset_l, int offset_r, boolean stranded) {

        if (DEBUG) {
            System.out.println("Scan map started");
        }

        if (ref_regions == null || ref_regions.isEmpty()) {
            return;
        }

        //define all variables;
        Iterator<Feature> iterator_ref_regions;
        Iterator<Feature> iterator_exp_regions;
        Feature exp_region;
        Feature ref_region;
        ArrayList<Feature> prev_inter_regions;

        ArrayList<Feature> temp;
        ArrayList<Feature> intersectings;

        //init iterator
        iterator_ref_regions = ref_regions.iterator();

        if (exp_regions == null || exp_regions.isEmpty()) {
            while (iterator_ref_regions.hasNext()) {
                ref_region = iterator_ref_regions.next();
                intersectings = new ArrayList<Feature>();
                finalizeOutputRegion(intersectings, ref_region);
            }
            return;
        }

        iterator_exp_regions = exp_regions.iterator();

        //select first experimental region
        exp_region = iterator_exp_regions.next();

        //init empty list for caching regions
        prev_inter_regions = new ArrayList<>();


        //iterate on the regions in the reference
        while (iterator_ref_regions.hasNext()) {
            //get reference region, udpate index and build the output structure
            ref_region = iterator_ref_regions.next();

            //make the new collection for the results            
            intersectings = new ArrayList<>();


            if (DEBUG) {
                System.out.println("_______________________________________________");
            }
            if (DEBUG) {
                System.out.println("Reference Region " + ref_region);
            }

            //advance the exp_region while in previous chromosome
//            while (iterator_exp_regions.hasNext()) {
//                if (exp_region.getChromosome().compareTo(ref_region.getChromosome()) < 0) {
//                    exp_region = iterator_exp_regions.next();
//                    if (DEBUG) {
//                        System.out.println("Skipped region :" + exp_region);
//                    }
//                } else {
//                    break;
//                }
//
//            }

            //check the cache
            if (!prev_inter_regions.isEmpty()) {
                temp = new ArrayList<>();
                for (Feature r : prev_inter_regions) {
                    //if there is intersection, we add the region
                    //both to the results and the cache
                    if (DEBUG) {
                        System.out.print("cache retrived " + r);
                    }
                    if (distanceLessThan(r, ref_region, offset_l, offset_r)) {
                        if (DEBUG) {
                            System.out.println(": kept");
                        }
                        //if (!stranded || r.getStrand().equals("*") || r.getStrand().equals(ref_region.getStrand()) || ref_region.getStrand().equals("*")) {
                        intersectings.add(r);
                        //}
                        temp.add(r);
                    } else if (!(r.stop < ref_region.start)) {
                        if (DEBUG) {
                            System.out.println(": kept");
                        }
                        temp.add(r);
                    } else if (DEBUG) {
                        System.out.println(": rejected");
                    }
                }
                prev_inter_regions = temp;
            }


            //iterate on exp regions. Break when no intersection
            //is found or when we overcome the current reference          
            while (true) {
                if (DEBUG) {
                    System.out.print("Exp region " + exp_region);
                }
                if (distanceLessThan(ref_region, exp_region, offset_l, offset_r)) {
                    //the region is inside, we process it
                    //if (!stranded || exp_region.getStrand().equals("*") || exp_region.getStrand().equals(ref_region.getStrand()) || ref_region.getStrand().equals("*")) {
                    intersectings.add(exp_region);
                    //}
                    //processResult(results, ref_regions_index, exp_region);
                    //we add also to tha cache if
                    //the region is not the last one ( or it will
                    //be checked two times with the next reference)                  
                    if (iterator_exp_regions.hasNext()) {
                        if (DEBUG) {
                            System.out.println(" cache added ");
                        }
                        prev_inter_regions.add(exp_region);
                    }
                } else if (DEBUG) {
                    System.out.println(" rejected ");
                }
                //chek if the region finish outside the reference                      
                int exp_left = exp_region.start;
                int ref_right = ref_region.stop;
                if (exp_left >= ref_right /*|| exp_region.getChromosome().compareTo(ref_region.getChromosome()) > 0*/) {
                    //in this case, we break and do not change the region
                    //what we need to change is reference!!
                    break;
                } else {
                    //else we go for next region, same reference
                    if (iterator_exp_regions.hasNext()) {
                        exp_region = iterator_exp_regions.next();
                    } //but if there are not regions after the current one,
                    //all we can do is check for the following references
                    else {
                        break;
                    }
                }
            } // end while on exp regions
            //add the result to the bag before going to the next reference
            finalizeOutputRegion(intersectings, ref_region);

            //System.out.println("map done on chr " + lastChrom + " total regions " + count);

        }// end while on reference       
    }

    private void finalizeOutputRegion(ArrayList<Feature> inters, Feature ref_region) {

        Feature ref_out_region = results.new Feature();
        //ref_out_region.setCoordinates(ref_region);
        ref_out_region.start = ref_region.start;
        ref_out_region.stop = ref_region.stop;
        ref_out_region.feature = ref_region.feature;


        /*for (String oldAttName : referenceAttributeNames) {
            Double val = ref_region.GetNumValue(oldAttName);
            ref_out_region.AddAttribute(oldAttName, val);
        }*/



        // List<Object> values = ref_region.getValues();
        NumberComparator nc = new NumberComparator();
        for (int i = 0; i < operations.size(); i++) {
            //System.out.println(operations.get(i) + " " + valueIndices.get(i));
            String index = valueIndices.get(i);
            switch (operations.get(i)) {
                case Count: {
                    ref_out_region.AddAttribute("COUNT", inters.size());
                    break;
                }
                case Exist: {
                    if (inters.isEmpty()) {
                        ref_out_region.AddAttribute("exist", 0);
                    } else {
                        ref_out_region.AddAttribute("exist", 1);
                    }
                    break;
                }
                case Min: {
                    Number min = null;

                    for (Feature inter : inters) {
                        Number n = (Number) inter.GetNumValue(index);
                        if (min == null) {
                            min = n;
                        } else if (n != null) {
                            if (n.floatValue() < min.floatValue()) {
                                min = n;
                            }
                        }
                    }
                    if (min != null) {
                        ref_out_region.AddAttribute("min_" + index, min.doubleValue());
                    } else {
                        ref_out_region.AddAttribute("min_" + index, Double.NaN);
                    }
                    break;
                }
                case Max: {
                    Number max = null;
                    for (Feature inter : inters) {
                        Number n = (Number) inter.GetNumValue(index);
                        if (max == null) {
                            max = n;
                        } else if (n != null) {
                            if (n.floatValue() > max.floatValue()) {
                                max = n;
                            }
                        }
                    }
                    if (max != null) {
                        ref_out_region.AddAttribute("max_" + index, max.doubleValue());
                    } else {
                        ref_out_region.AddAttribute("max_" + index, Double.NaN);
                    }
                    break;
                }
                case Sum: {
                    Number sum = null;
                    for (Feature inter : inters) {
                        sum = add(sum, (Number) inter.GetNumValue(index));
                    }
                    if (sum != null) {
                        ref_out_region.AddAttribute("sum_" + index, sum.doubleValue());
                    } else {
                        ref_out_region.AddAttribute("sum_" + index, Double.NaN);
                    }
                    break;
                }
                case Average: {
                    Number sum = null;
                    int count = 0;
                    for (Feature inter : inters) {
                        Number n = (Number) inter.GetNumValue(index);
                        sum = add(sum, n);
                        if (n != null) {
                            ++count;
                        }
                    }
                    Double avg = null;
                    if (count > 0) {
                        avg = sum.doubleValue() / count;
                    }
                    if (avg != null) {
                        ref_out_region.AddAttribute("avg_" + index, avg.doubleValue());
                    } else {
                        ref_out_region.AddAttribute("avg_" + index, Double.NaN);
                    }
                    break;
                }
                    //BAG DISABLED WHILE ONLY DOUBLE VALUE IS PERMITTED
               /* case BAG: {
                    String bag = null;
                    for (Feature inter : inters) {
                        if (bag == null) {
                            bag = inter.GetNumValue(index).toString();
                        } else {
                            bag = bag + "___" + inter.getValue(index);
                        }
                    }
                    values.add(bag);
                    break;
                }*/

                case Median: {
                    List<Number> nums = new ArrayList<>();
                    for (Feature inter : inters) {
                        Number n = (Number) inter.GetNumValue(index);
                        if (n != null) {
                            nums.add(n);
                        }
                    }
                    Collections.sort(nums, nc);
                    //System.out.println(nums);
                    ref_out_region.AddAttribute("median_" + index, getMedian(nums).doubleValue());
                    break;
                }
                case StandardDeviation: {
                    List<Number> nums = new ArrayList<>();
                    for (Feature inter : inters) {
                        Number n = (Number) inter.GetNumValue(index);
                        if (n != null) {
                            nums.add(n);
                        }
                    }
                    double mean = 0;
                    for (Number num : nums) {
                        mean += num.doubleValue();
                    }
                    mean = mean / nums.size();
                    for (int j = 0; j < nums.size(); j++) {
                        Number newN = Math.pow(nums.get(j).doubleValue() - mean, 2);
                        nums.set(j, newN);
                    }
                    mean = 0.0;
                    for (Number num : nums) {
                        mean += num.doubleValue();
                    }
                    mean = mean / nums.size();
                    ref_out_region.AddAttribute("std_" + index, Math.sqrt(mean));

                }
            }
        }
        results.features.get(currentChrom).add(ref_out_region);
    }

    public SampleData getResults() {
        return this.results;
    }

    private Number getMedian(List<Number> nums) {

        if (nums.isEmpty()) {
            return null;
        }

        if (nums.size() % 2 == 0) {
            Number n1 = nums.get((nums.size() / 2) - 1);
            Number n2 = nums.get((nums.size() / 2));
            Number m = add(n1, n2);
            return m.floatValue() / 2;
        } else {
            return nums.get((nums.size() / 2));
        }

    }

    private class NumberComparator implements Comparator<Number> {

        @Override
        public int compare(Number o1, Number o2) {
            float comp = o1.floatValue() - o2.floatValue();
            if (comp < 0) {
                return -1;
            }
            if (comp > 0) {
                return 1;
            }
            return 0;
        }
    }

    private Number add(Number total, Number value) {
        if (value == null) {
            return total;
        }
        if (value instanceof Integer) {
            Integer _total = (Integer) total;
            Integer _value = (Integer) value;
            if (total == null) {
                return _value;
            }
            return _total + _value;
        }
        if (value instanceof Double) {
            Double _total = (Double) total;
            Double _value = (Double) value;
            if (total == null) {
                return _value;
            }
            return _total + _value;
        }
        if (value instanceof Float) {
            Float _total = (Float) total;
            Float _value = (Float) value;
            if (total == null) {
                return _value;
            }
            return _total + _value;
        }
        if (value instanceof Long) {
            Long _total = (Long) total;
            Long _value = (Long) value;
            if (total == null) {
                return _value;
            }
            return _total + _value;
        }
        return null;
    }

    public static boolean distanceLessThan(Feature r1, Feature r2, int offset_l, int offset_r) {
        int t1_left = r1.start;
        t1_left -= offset_l;
        int t1_right = r1.stop;
        t1_right += offset_r;
        int t2_left = r2.start;
        int t2_right = r2.stop;
        //return (t1_left <= t2_right && t1_right >= t2_left);
        return (t1_left < t2_right && t1_right > t2_left);
    }

    private class FeatureComparator implements Comparator<Feature> {

        @Override
        public int compare(Feature o1, Feature o2) {
            Integer start1 = o1.start;
            Integer stop1 = o1.stop;
            Integer start2 = o2.start;
            Integer stop2 = o2.stop;

            if (start1 < start2) {
                return -1;
            } else if (start1 > start2) {
                return 1;
            } else if (stop1 < stop2) {
                return -1;
            } else if (stop1 > stop2) {
                return 1;
            }

            return 0;
        }
    }
}

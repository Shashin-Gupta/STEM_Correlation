import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Shashin Gupta
 * @category Synopsis Science Fair
 */

public class Coordinator {

    public static final String WILDFIRE_PATH = "/Users/shuchigarg/IdeaProjects/EyeLight/src/Wildire_Information/";
    public static final String HURRICANE_PATH = "/Users/shuchigarg/IdeaProjects/EyeLight/src/Hurricane_Information/";
    private static LinkedList<DataSet> cost_hurricanes, cost_wildfires, count_hurricanes, count_wildfires,
                                       deaths_hurricanes, deaths_wildfires, acresBurned_wildfires, intensity_hurricanes;
    private static int[][] years;
    private static double[][] data;
    private static FileInterpreter[] fis;

    static {
        cost_hurricanes = new LinkedList<DataSet>();
        cost_wildfires = new LinkedList<DataSet>();
        fis = new FileInterpreter[8];
        fis[0] = new FileInterpreter(WILDFIRE_PATH + "WildfireCostOfSupression.txt");
        fis[1] = new FileInterpreter(HURRICANE_PATH + "HurricaneCost.txt");
        fis[2] = new FileInterpreter(WILDFIRE_PATH + "WildfireCount.txt");
        fis[3] = new FileInterpreter(HURRICANE_PATH + "HurricaneCount.txt");
        fis[4] = new FileInterpreter(WILDFIRE_PATH + "WildfireFatalities.txt");
        fis[5] = new FileInterpreter(HURRICANE_PATH + "HurricaneDeaths.txt");
        // fis[6] = new FileInterpreter(WILDFIRE_PATH + "WildfireAcresBurned.txt");
        // fis[7] = new FileInterpreter(HURRICANE_PATH + "HurricaneIntensity.txt");
        years = new int[8][]; // The number eight is just an approximation. This may be changed later depending on how much more/less we use
        data = new double[8][];
    }

    /**
     * Index Dictionary
     * <p>
     * 0 -> Cost for Wildfires
     * 1 -> Cost for Hurricanes
     * 2 -> Wildfire Count
     * 3 -> Hurricane Count
     * 4 -> Wildfire Fatalities
     * 5 -> Hurricane Fatalities
     * 6 -> Wildfire Acres Burned
     * 7 -> Hurricane Intensity
     */

    // For 6 and 7 we find no way to compare the data in a way that works so we decided to just leave it h
    public static void main(String[] args) {
        cost_wildfires = fis[0].load();
        cost_hurricanes = fis[1].load();
        years[0] = getQuantitativeData(cost_wildfires);
        years[1] = getQuantitativeData(cost_hurricanes);
        data[0] = getQuantitativeData(cost_wildfires, false);
        data[1] = getQuantitativeData(cost_hurricanes, false);

        count_wildfires = fis[2].load();
        count_hurricanes = fis[3].load();
        years[2] = getQuantitativeData(count_wildfires);
        years[3] = getQuantitativeData(count_hurricanes);
        data[2] = getQuantitativeData(count_wildfires, false);
        data[3] = getQuantitativeData(count_hurricanes, false);

        deaths_wildfires = fis[4].load();
        deaths_hurricanes = fis[5].load();
        years[4] = getQuantitativeData(deaths_wildfires);
        years[5] = getQuantitativeData(deaths_hurricanes);
        data[4] = getQuantitativeData(deaths_wildfires, false);
        data[5] = getQuantitativeData(deaths_hurricanes, false);

        acresBurned_wildfires = fis[6].load();
        intensity_hurricanes = fis[7].load();
        years[6] = getQuantitativeData(acresBurned_wildfires);
        years[7] = getQuantitativeData(intensity_hurricanes);
        data[6] = getQuantitativeData(acresBurned_wildfires, false);
        data[7] = getQuantitativeData(intensity_hurricanes, false);

    }

    private static double[] getQuantitativeData(LinkedList<DataSet> data, boolean year) {
        double[] numericalData = new double[data.size()];

        Iterator<DataSet> iter = data.iterator();

        int i = 0;
        while (iter.hasNext()) numericalData[i] = iter.next().getData();
        return numericalData;
    }

    private static int[] getQuantitativeData(LinkedList<DataSet> data) {
        int[] numericalData = new int[data.size()];

        Iterator<DataSet> iter = data.iterator();

        int i = 0;
        while (iter.hasNext()) numericalData[i] = iter.next().getYear();
        return numericalData;
    }

    private static class FileInterpreter {
        private Scanner sc;

        public FileInterpreter(String path) {
            try {
                sc = new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                System.out.println("File: " + path + "raised FileNotFoundException");

            }
        }
            public LinkedList<DataSet> load () {

                LinkedList<DataSet> tokens = new LinkedList<DataSet>();
                StringTokenizer st;
                String line;
                int year;
                double data;

                sc.nextLine();
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    st = new StringTokenizer(line, " ");

                    year = Integer.parseInt(st.nextToken());
                    data = Double.parseDouble(st.nextToken());
                    tokens.add(new DataSet(year, data));
                }

                return tokens;
            }
    }
}

package RestaurantDataLayer;

import RestaurantBusinessLayer.BaseProduct;
import RestaurantBusinessLayer.DeliveryService;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileWriter {

    public static List<String> readCsv() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\edmon\\Downloads\\archive (1)\\epi_r.csv"));

          List<String> input = br.lines()
                  .skip(1)
                .flatMap(s -> Arrays.stream(s.split(",")))
                .collect(Collectors.toList());

          DeliveryService main = DeliveryService.getInstance();

          for(int i=0; i<input.size(); i+= 7)
          {
              System.out.println(input.get(i+1));
               main.importProduct(input.get(i),
                      Float.parseFloat(input.get(i+1)),
                      Integer.parseInt(input.get(i+2)),
                      Integer.parseInt(input.get(i+3)),
                      Integer.parseInt(input.get(i+4)),
                      Integer.parseInt(input.get(i+5)),
                      Integer.parseInt(input.get(i+6)));

              System.out.println(input.get(i));
          }

         return input;
    }


    public static void updateMenu() throws IOException {

        DeliveryService main = DeliveryService.getInstance();

        try
        {
            File file=new File("C:\\Users\\edmon\\Desktop\\Restaurant Backend\\Restaurant\\src\\RestaurantBusinessLayer\\input.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer();
            String line;
            int index = 0;
            while((line=br.readLine())!=null)
            {
                String[] arrOfStr = line.split(",");

                main.importProduct(arrOfStr[0],
                        Float.parseFloat(arrOfStr[1]),
                        Integer.parseInt(arrOfStr[2]),
                        Integer.parseInt(arrOfStr[3]),
                        Integer.parseInt(arrOfStr[4]),
                        Integer.parseInt(arrOfStr[5]),
                        Integer.parseInt(arrOfStr[6]));

                index++;
                sb.append(line);
                sb.append("\n");

            }
            fr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}

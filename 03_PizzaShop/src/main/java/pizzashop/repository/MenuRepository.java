package pizzashop.repository;

import pizzashop.model.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private static String filename = "src/main/java/pizzashop/files/menus.txt";
    private List<Menu> listMenu;

    private void readMenu(){
        //ClassLoader classLoader = MenuRepository.class.getClassLoader();
        //File file = new File(classLoader.getResource(filename).getFile());
        this.listMenu= new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line=br.readLine())!=null){
                Menu menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Menu getMenuItem(String line){
        Menu item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new Menu(name, 0, price);
        return item;
    }

    public List<Menu> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }



}
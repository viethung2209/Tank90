package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieStore;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public Item[][] readAllItems(String path) {
        Item[][] items = new Item[Const.NUMBER_ROW][Const.NUMBER_COLUMN];
        URL url = ReadFile.class.getResource(
                "/Map/" + path
        );
        try {
            InputStream inputStream = url.openStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            inputStream
                    )
            );
            String line = in.readLine();
            int row = 0;
            while (line != null) {

                for (int i = 0; i < line.length(); i++) {
                    Item item = new Item();
                    int key = line.charAt(i) - '0';
                    item.setTypeID(key);
                    item.setX(i * Const.SIZE_TIME);
                    item.setY(row * Const.SIZE_TIME);
                    items[row][i] = item;
                    if (key == 0) {
                        continue;
                    }
                    item.setW(Const.SIZE_TIME);
                    item.setH(Const.SIZE_TIME);
                    item.setImage(item.getImage(key));

                    if (key == 9) {
                        item.setW(Const.SIZE_TIME * 2);
                        item.setH(Const.SIZE_TIME * 2);

                    }
                }
                line = in.readLine();
                row++;
            }

            in.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
    public Item findHome(String  path) {
        Item Home = new Item();
        URL url = ReadFile.class.getResource(
                "/Map/" + path
        );
        try {
            InputStream inputStream = url.openStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            inputStream
                    )
            );
            String line = in.readLine();
            int row = 0;
            while (line != null) {

                for (int i = 0; i < line.length(); i++) {
                    Item item = new Item();
                    int key = line.charAt(i) - '0';
                    if (key == 9) {
                        item.setTypeID(key);
                        item.setW(Const.SIZE_TIME * 2);
                        item.setH(Const.SIZE_TIME * 2);
                        item.setX(i * Const.SIZE_TIME);
                        item.setY(row * Const.SIZE_TIME);
                        Home = item;
                    }
                }
                line = in.readLine();
                row++;
            }

            in.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Home;
    }
    public List<Item> checkAppear(Item[][] items, int w , int h){
        List<Item> items1 = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < w; i++) {
            if (i == k) {
                continue;
            }
            for (int j = 0; j < h; j++) {
               // items[i][j].getTypeID() != Item.BRICK && items[i][j].getTypeID() != Item.HOME && items[i][j].getTypeID() != Item.ROCK
                if (items[i][j].getTypeID() == Item.TREE || items[i][j].getTypeID() == 0 ) {
                    if (items[i ][j + 1].getTypeID() == Item.TREE || items[i ][j + 1].getTypeID() == 0){
                        if (items[i + 1][j ].getTypeID() == Item.TREE || items[i + 1][j].getTypeID() == 0){
                            if (items[i + 1][j + 1].getTypeID() == Item.TREE || items[i + 1][j + 1].getTypeID() == 0){
                                items1.add(items[i][j]);
                                j = j + 1;
                                k = i + 1;
                            }
                        }
                    }
                }
            }
        }
        return items1;
    }
}

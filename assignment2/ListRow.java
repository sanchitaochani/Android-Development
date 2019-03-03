package hw.appdev.example.android.assignment2;

public class ListRow {
    int img_id;
    String myText;
    boolean isSelected = false;
    int rotation = 0;
    int flip = 0;
    float translate_x_right = 0;
    float translate_x_left = 0;
    float translate_y_up = 0;
    float translate_y_down = 0;
    float center = 0;

    public ListRow(int image_id, String text) {
        img_id = image_id;
        myText = text;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
    }
}

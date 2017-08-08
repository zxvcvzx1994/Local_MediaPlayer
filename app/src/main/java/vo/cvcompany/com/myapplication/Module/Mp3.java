package vo.cvcompany.com.myapplication.Module;

/**
 * Created by kh on 8/7/2017.
 */

public class Mp3 {
    private String title ;
    private int link;

    public Mp3(){

    }
    public Mp3(String title, int link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }
}

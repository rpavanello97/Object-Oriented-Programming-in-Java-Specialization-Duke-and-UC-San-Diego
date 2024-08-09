import edu.duke.URLResource;

@SuppressWarnings("IndexOfReplaceableByContains")
public class Part4 {
    private void getURLsFromWeb() {
        URLResource resource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        int ybCount = 0;
        for (String line : resource.lines()) {
            if(line.toLowerCase().indexOf("youtube.com") > -1) {

                int ybIndex = line.toLowerCase().indexOf("youtube.com");
                int startIndex = line.lastIndexOf("\"",ybIndex);
                int finalIndex = line.indexOf("\"",startIndex + 1); //+1 to not include double quote

                String link = line.substring(startIndex + 1, finalIndex);

                System.out.println("Youtube link is: "+link);
            }
        }
    }
    public static void main (String[] args) {
        Part4 p4 = new Part4();
        p4.getURLsFromWeb();
    }
}

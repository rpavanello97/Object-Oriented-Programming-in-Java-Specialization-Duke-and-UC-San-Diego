import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public static double truncateDecimal(double value, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return (long)(value * factor) / factor;
    }

    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return truncateDecimal(totalPerim,2);
    }

    public int getNumPoints (Shape s) {
        // This method returns an integer that is the number of points in Shape s.
        int totPoints = 0;
        for (Point p : s.getPoints()) totPoints++;
        return totPoints;
    }

    public double getAverageLength(Shape s) {
        //This method returns a number of type double that is the calculated average of all the sides’ lengths in the Shape S.
        double perimeter = getPerimeter(s);
        int totPoints = getNumPoints(s);

        double avg;
        avg = perimeter / totPoints;

        return truncateDecimal(avg,2);
    }

    public double getLargestSide(Shape s) {
        //This method returns a number of type double that is the longest side in the Shape S.
        double largestSide = 0.0;

        for(Point currPoint : s.getPoints()) {
            double length = currPoint.distance(s.getLastPoint());
            if (length > largestSide) {
                largestSide = length;
            }
        }
        return truncateDecimal(largestSide,2);
    }

    public double getLargestX(Shape s) {
        //This method returns a number of type double that is the largest x value over all the points in the Shape s.
        double largestX = 0.0;
        for(Point currPoint : s.getPoints()) {
            double currentX = currPoint.getX();
            if (currentX > largestX) {
                largestX = currentX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Return the largest perimeter over all the shapes in the files you have selected.
        double largPerimMultFiles = 0.0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);

            double currentPerimeter = getPerimeter(s);

            if (currentPerimeter > largPerimMultFiles)
                largPerimMultFiles = currentPerimeter;
        }

        return truncateDecimal(largPerimMultFiles,2);
    }

    public String getFileWithLargestPerimeter() {
        // Returns the name of the File that has the largest such perimeter, so it has return type String.
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        File file = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);

            double currentPerimeter = getPerimeter(s);

            if (currentPerimeter > largestPerimeter) {
                file = f;
                largestPerimeter = currentPerimeter;
            }
        }

        assert file != null;
        return file.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);

        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int totPoints = getNumPoints(s);
        System.out.println("total points = " + totPoints);

        double avg = getAverageLength(s);
        System.out.println("Average is = " + avg);

        double largestSide = getLargestSide(s);
        System.out.println("Largest side is = " + largestSide);

        double largestX = getLargestX(s);
        System.out.println("Largest X is = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeterMultipleFiles = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is = " + largestPerimeterMultipleFiles);
    }

    public void testFileWithLargestPerimeter() {
        String fileName = getFileWithLargestPerimeter();
        System.out.println("File name with largest perimeter = " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}

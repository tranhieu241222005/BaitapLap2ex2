package iuh.fit.KTPM;
/**
 * @description : This is the property of the rectangle
 * @author: Tran Hieu
 * @version: 1.0
 * @created :  25/08/2024 9:17 SA
 */
public class Rectangle {
    //Atributes
    private double length;
    private double width;
    public Rectangle(){
        this.length = 0;
        this.width = 0;
    }
    /**
     * @param length
     * @param width
     * @throws IllegalArgumentException
     */
    public Rectangle(double length, double width){
        if(length < 0  || width < 0)
            throw new ArithmeticException("Length and width must be greater than 0");
        this.length = length;
        this.width = width;
    }
    // setters /getters
    public double getLength() {
        return length;
    }
    /**
     * Description :Set the length of the Rectangle
     * @param length th length of the Rectangle
     * @throws IllegalAccessException if length is less than 0
     */
    public void setLength(double length) {
        if( length < 0)
            throw new IllegalArgumentException("Length must be greater than 0");
        this.length = length;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        if( width < 0)
            throw new IllegalArgumentException("Width must be greater than 0");
        this.width = width;
    }
    //Methods
    public double getArea(){
        return this.length * this.width;
    }
    public double getPerimeter(){
        return (this.length + this.width)*2;
    }
    @Override
    public String toString  () {
        return String.format("Rectangle [length=%.1f, width=%.1f, getArea=%.1f,getPerimeter=%.1f]", length, width,getArea(),getPerimeter());
    }

}


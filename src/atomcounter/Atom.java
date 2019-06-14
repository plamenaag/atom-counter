package atomcounter;

public class Atom {

    private String elementName;
    private Integer count;

    public Atom(char ch) {
        this.elementName = "" + ch;
        this.count = 1;
    }

    public String getElementName() {
        if (elementName == null) {
            elementName = "";
        }
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public void addChar(char ch) {
        this.elementName = this.getElementName() + ch;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}

package sagar.khengat.digitallibrary.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Book implements Parcelable {
    @DatabaseField(canBeNull = true)

    private String bookName;
    @DatabaseField(canBeNull = true)
    private String bookCategory;

    @DatabaseField(foreign=true, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=3)

    private Faculty bookFaculty;
    @DatabaseField(foreign=true, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=3)

    private Student bookStudent;

    @DatabaseField(canBeNull = true)

    private String bookPublicationHouse;


    @DatabaseField(canBeNull = true,id = true)

    private String bookId;


    @DatabaseField(canBeNull = true)

    private String bookAuther;


    @DatabaseField(canBeNull = true)


    private double bookOriginalPrice;

    @DatabaseField(canBeNull = true)


    private int bookPages;

    @DatabaseField(canBeNull = true)


    private boolean available = true;

    @DatabaseField(canBeNull = true)


    private boolean issued = false;

    public boolean issued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Faculty getBookFaculty() {
        return bookFaculty;
    }

    public void setBookFaculty(Faculty bookFaculty) {
        this.bookFaculty = bookFaculty;
    }

    public Student getBookStudent() {
        return bookStudent;
    }

    public void setBookStudent(Student bookStudent) {
        this.bookStudent = bookStudent;
    }

    public String getBookPublicationHouse() {
        return bookPublicationHouse;
    }

    public void setBookPublicationHouse(String bookPublicationHouse) {
        this.bookPublicationHouse = bookPublicationHouse;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookAuther() {
        return bookAuther;
    }

    public void setBookAuther(String bookAuther) {
        this.bookAuther = bookAuther;
    }

    public double getBookOriginalPrice() {
        return bookOriginalPrice;
    }

    public void setBookOriginalPrice(double bookOriginalPrice) {
        this.bookOriginalPrice = bookOriginalPrice;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                ", bookFaculty=" + bookFaculty +
                ", bookStudent=" + bookStudent +
                ", bookPublicationHouse='" + bookPublicationHouse + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookAuther='" + bookAuther + '\'' +
                ", bookOriginalPrice=" + bookOriginalPrice +
                ", bookPages=" + bookPages +
                ", available=" + available +
                ", issued=" + issued +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookId);
        dest.writeInt(bookPages);
        dest.writeString(bookAuther);
        dest.writeDouble(bookOriginalPrice);

        dest.writeString(bookName);
        dest.writeValue(available);
        dest.writeValue(issued);

        dest.writeValue(bookFaculty);
        dest.writeValue(bookStudent);
        dest.writeString(bookCategory);
        dest.writeString(bookPublicationHouse);

    }
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    public Book(Parcel in) {
        bookId = in.readString();
        bookName = in.readString();
        bookFaculty = (Faculty) in.readValue(Book.class.getClassLoader());

        bookStudent = (Student) in.readValue(Book.class.getClassLoader());
        bookPages = in.readInt();
        bookPublicationHouse = in.readString();
        bookAuther = in.readString();
        bookCategory = in.readString();
        bookOriginalPrice = in.readDouble();
        available = (Boolean)in.readValue(Book.class.getClassLoader());
        issued = (Boolean)in.readValue(Book.class.getClassLoader());

    }

    public Book() {
    }


}

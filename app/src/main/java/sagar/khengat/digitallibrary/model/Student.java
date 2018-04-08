package sagar.khengat.digitallibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Sagar Khengat on 08/04/2018.
 */

public class Student implements Parcelable {
    @DatabaseField(canBeNull = true,id = true)

    private String studentId;
    @DatabaseField(canBeNull = true)
    private String name;
    @DatabaseField(canBeNull = true,unique = true)
    private String mobno;
    @DatabaseField(canBeNull = true)
    private String address;
    @DatabaseField(canBeNull = true)
    private String password;
    @DatabaseField(canBeNull = true)
    private String branch;
    @DatabaseField(canBeNull = true)
    private String email;


    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String id) {
        this.studentId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentId);
        dest.writeString(name);
        dest.writeString(mobno);
        dest.writeString(password);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(branch);



    }

    public static final Parcelable.Creator<Faculty> CREATOR = new Parcelable.Creator<Faculty>() {
        @Override
        public Faculty createFromParcel(Parcel source) {
            return new Faculty(source);
        }

        @Override
        public Faculty[] newArray(int size) {
            return new Faculty[size];
        }
    };
    public Student(Parcel in) {
        name = in.readString();
        mobno = in.readString();
        password = in.readString();
        address = in.readString();
        email = in.readString();
        branch = in.readString();
        studentId = in.readString();

    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", mobno='" + mobno + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", branch='" + branch + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Student() {
    }
}

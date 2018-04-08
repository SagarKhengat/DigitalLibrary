package sagar.khengat.digitallibrary.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Faculty implements Parcelable {
    @DatabaseField(canBeNull = true,id = true)

    private String facultyId;
    @DatabaseField(canBeNull = true)
    private String name;
    @DatabaseField(canBeNull = true,unique = true)
    private String mobno;
    @DatabaseField(canBeNull = true)
    private String address;
    @DatabaseField(canBeNull = true)
    private String password;

    @DatabaseField(canBeNull = true)
    private String email;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }


    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
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
        dest.writeString(facultyId);
        dest.writeString(name);
        dest.writeString(mobno);
        dest.writeString(password);
        dest.writeString(address);
        dest.writeString(email);



    }

    public static final Creator<Faculty> CREATOR = new Creator<Faculty>() {
        @Override
        public Faculty createFromParcel(Parcel source) {
            return new Faculty(source);
        }

        @Override
        public Faculty[] newArray(int size) {
            return new Faculty[size];
        }
    };
    public Faculty(Parcel in) {
        name = in.readString();
        mobno = in.readString();
        password = in.readString();
        address = in.readString();
        email = in.readString();
        facultyId = in.readString();

    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", name='" + name + '\'' +
                ", mobno='" + mobno + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Faculty() {
    }
}

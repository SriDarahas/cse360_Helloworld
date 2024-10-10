package application.utils;

import application.models.InvitationCode;
import application.models.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static final String USER_DATA_FILE = "users.dat";
    private static final String INVITE_DATA_FILE = "invites.dat";

    private static Map<String, User> users = new HashMap<>();
    private static Map<String, InvitationCode> invites = new HashMap<>();

    public static void loadUserData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            users = new HashMap<>();
        }
    }

    public static void saveUserData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadInviteData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(INVITE_DATA_FILE))) {
            invites = (Map<String, InvitationCode>) ois.readObject();
        } catch (Exception e) {
            invites = new HashMap<>();
        }
    }

    public static void saveInviteData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INVITE_DATA_FILE))) {
            oos.writeObject(invites);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methods to manage users and invites
    public static Map<String, User> getUsers() {
        return users;
    }

    public static Map<String, InvitationCode> getInvites() {
        return invites;
    }
}

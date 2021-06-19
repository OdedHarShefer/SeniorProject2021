package com.example.seniorproject2021;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dal extends SQLiteAssetHelper {
    public Dal(Context context) {
        super(context, "my_db.db", null, 1);
    }

    public void addProvider(String name, String profession, byte[] picture, String gender, String misc, int account_id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO providers (name, profession, picture, gender, misc, score, account_id, score_count) values(?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, name);
        statement.bindString(2, profession);
        statement.bindBlob(3, picture);
        statement.bindString(4, gender);
        statement.bindString(5,  misc);
        statement.bindDouble(6, 0.0 );
        statement.bindDouble(7, account_id);
        statement.bindDouble(8, 0);
        statement.execute();
    }

    public void updateProvider(int id, String name, String profession, byte[] picture, String gender, String misc, double score, int scoreCount) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_UPDATE = "UPDATE providers SET name = ?, profession = ?, picture = ?, gender = ?, misc = ?, score = ?, score_count = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql_UPDATE);

        statement.bindString(1, name);
        statement.bindString(2, profession);
        statement.bindBlob(3, picture);
        statement.bindString(4, gender);
        statement.bindString(5,  misc);
        statement.bindDouble(6, score );
        statement.bindDouble(7, scoreCount);
        statement.bindDouble(8, id);

        statement.execute();
    }

    public ArrayList<Provider> getAllProvidersByProfession(String profession, int customerId) {
        ArrayList<Provider> ary = new ArrayList<>();
        String st = "SELECT * FROM providers WHERE profession LIKE '%" + profession + "%'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Provider p = new Provider();
            p.setId(cursor.getInt(cursor.getColumnIndex("id")));
            p.setName(cursor.getString(cursor.getColumnIndex("name")));
            p.setProfession(cursor.getString(cursor.getColumnIndex("profession")));
            p.setImage(cursor.getBlob(cursor.getColumnIndex("picture")));
            p.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            p.setMisc(cursor.getString(cursor.getColumnIndex("misc")));
            p.setScore(cursor.getDouble(cursor.getColumnIndex("score")));
            p.setAccountId(cursor.getInt(cursor.getColumnIndex("account_id")));
            p.setScoreCount(cursor.getInt(cursor.getColumnIndex("score_count")));
            ary.add(p);
        }
        int i = 0;
        int size = ary.size();
        int count = 0;
        while (!ary.isEmpty() && i < size - count) {
            if (checkContact(ary.get(i).getId(), customerId)) {
                ary.remove(i);
                count++;
            } else
                i++;
        }
        return ary;
    }

    public ArrayList<Provider> getAllProvidersByName(String name, int customerId) {
        ArrayList<Provider> ary = new ArrayList<>();
        String st = "SELECT * FROM providers WHERE name LIKE '%" + name + "%'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Provider p = new Provider();
            p.setId(cursor.getInt(cursor.getColumnIndex("id")));
            p.setName(cursor.getString(cursor.getColumnIndex("name")));
            p.setProfession(cursor.getString(cursor.getColumnIndex("profession")));
            p.setImage(cursor.getBlob(cursor.getColumnIndex("picture")));
            p.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            p.setMisc(cursor.getString(cursor.getColumnIndex("misc")));
            p.setScore(cursor.getDouble(cursor.getColumnIndex("score")));
            p.setAccountId(cursor.getInt(cursor.getColumnIndex("account_id")));
            p.setScoreCount(cursor.getInt(cursor.getColumnIndex("score_count")));
            ary.add(p);
        }
        int i = 0;
        int size = ary.size();
        int count = 0;
        while (!ary.isEmpty() && i < size - count) {
            if (checkContact(ary.get(i).getId(), customerId)) {
                ary.remove(i);
                count++;
            } else
                i++;
        }
        return ary;
    }

    public Provider getProviderById(int id) {
        String st = "SELECT * FROM providers WHERE id = " + id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Provider p = new Provider();
        cursor.moveToNext();
        p.setId(cursor.getInt(cursor.getColumnIndex("id")));
        p.setName(cursor.getString(cursor.getColumnIndex("name")));
        p.setProfession(cursor.getString(cursor.getColumnIndex("profession")));
        p.setImage(cursor.getBlob(cursor.getColumnIndex("picture")));
        p.setGender(cursor.getString(cursor.getColumnIndex("gender")));
        p.setMisc(cursor.getString(cursor.getColumnIndex("misc")));
        p.setScore(cursor.getDouble(cursor.getColumnIndex("score")));
        p.setAccountId(cursor.getInt(cursor.getColumnIndex("account_id")));
        p.setScoreCount(cursor.getInt(cursor.getColumnIndex("score_count")));

        return p;
    }

    public Provider getProviderByAccountId(int accountId) {
        String st = "SELECT * FROM providers WHERE account_id = " + accountId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Provider p = new Provider();
        cursor.moveToNext();
        p.setId(cursor.getInt(cursor.getColumnIndex("id")));
        p.setName(cursor.getString(cursor.getColumnIndex("name")));
        p.setProfession(cursor.getString(cursor.getColumnIndex("profession")));
        p.setImage(cursor.getBlob(cursor.getColumnIndex("picture")));
        p.setGender(cursor.getString(cursor.getColumnIndex("gender")));
        p.setMisc(cursor.getString(cursor.getColumnIndex("misc")));
        p.setScore(cursor.getDouble(cursor.getColumnIndex("score")));
        p.setAccountId(cursor.getInt(cursor.getColumnIndex("account_id")));
        p.setScoreCount(cursor.getInt(cursor.getColumnIndex("score_count")));

        return p;
    }

    public boolean checkForProvider(int accountId) {
        String st = "SELECT * FROM providers WHERE account_id = " + accountId ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        if (cursor.getCount() == 1)
            return true;
        return false;
    }

    public void addCustomer(String name, byte[] picture, String gender, int accountId) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO customers (name, picture, gender, account_id) values(?,?,?," + accountId + ")";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, name);
        statement.bindBlob(2, picture);
        statement.bindString(3, gender);
        statement.execute();
    }

    public void updateCustomer(int id, String name, byte[] picture, String gender) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_UPDATE = "UPDATE customers SET name = ?, picture = ?, gender = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql_UPDATE);

        statement.bindString(1, name);
        statement.bindBlob(2, picture);
        statement.bindString(3, gender);
        statement.bindDouble(4, id);
        statement.execute();
    }

    public ArrayList<Customer> getAllCustomersByName(String name, int providerId) {
        ArrayList<Customer> ary = new ArrayList<>();
        String st = "SELECT * FROM customers WHERE name LIKE '%" + name +"%'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Customer c = new Customer();
            c.setId(cursor.getInt(cursor.getColumnIndex("id")));
            c.setName(cursor.getString(cursor.getColumnIndex("name")));
            c.setImage(cursor.getBlob(cursor.getColumnIndex("picture")));
            c.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            c.setAccountId(cursor.getInt(cursor.getColumnIndex("account_id")));

            ary.add(c);
        }
        int i = 0;
        int size = ary.size();
        int count = 0;
        while (!ary.isEmpty() && i < size - count) {
            if (checkContact(providerId, ary.get(i).getId())) {
                ary.remove(i);
                count++;
            }
            else
                i++;
        }
        return ary;
    }

    public Customer getCustomerById(int id) {
        String st = "SELECT * FROM customers WHERE id = " + id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Customer c = new Customer();
        cursor.moveToNext();
        c.setId(cursor.getInt(cursor.getColumnIndex("id")));
        c.setName(cursor.getString(cursor.getColumnIndex("name")));
        c.setImage(cursor.getBlob(cursor.getColumnIndex("picture")));
        c.setGender(cursor.getString(cursor.getColumnIndex("gender")));
        c.setAccountId(cursor.getInt(cursor.getColumnIndex("account_id")));

        return c;
    }

    public Customer getCustomerByAccountId(int AccountId) {
        String st = "SELECT * FROM customers WHERE account_id = " + AccountId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Customer c = new Customer();
        cursor.moveToNext();
        c.setId(cursor.getInt(cursor.getColumnIndex("id")));
        c.setName(cursor.getString(cursor.getColumnIndex("name")));
        c.setImage(cursor.getBlob(cursor.getColumnIndex("picture")));
        c.setGender(cursor.getString(cursor.getColumnIndex("gender")));
        c.setAccountId(cursor.getInt(cursor.getColumnIndex("account_id")));

        return c;
    }

    public boolean checkForCustomer(int accountId) {
        String st = "SELECT * FROM customers WHERE account_id = " + accountId ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        if (cursor.getCount() == 1)
            return true;
        return false;
    }

    public void addAccount(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO accounts (email, password) values(?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, email);
        statement.bindString(2, password);
        statement.execute();
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> ary = new ArrayList<>();
        String st = "SELECT * FROM accounts";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Account a = new Account();
            a.setId(cursor.getInt(cursor.getColumnIndex("id")));
            a.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            a.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            ary.add(a);
        }
        return ary;
    }

    public boolean checkForAccount(String email, String password) {
        String st = "SELECT * FROM accounts WHERE (email = '" + email + "' AND password = '" + password + "')";
        SQLiteDatabase db = getWritableDatabase();
        if (db.rawQuery(st, null).getCount() == 1)
            return true;
        return false;
    }

    public boolean checkForAccount(String email) {
        String st = "SELECT * FROM accounts WHERE (email = '" + email + "')";
        SQLiteDatabase db = getWritableDatabase();
        if (db.rawQuery(st, null).getCount() == 1)
            return true;
        return false;
    }

    public Account getAccount(int id) {
        String st = "SELECT * FROM accounts WHERE id = " + id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Account a = new Account();
        cursor.moveToNext();
        a.setId(cursor.getInt(cursor.getColumnIndex("id")));
        a.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        a.setPassword(cursor.getString(cursor.getColumnIndex("password")));

        return a;
    }

    public Account getAccount(String email) {
        String st = "SELECT * FROM accounts WHERE email = ?";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, new String[]{email});
        Account a = new Account();
        cursor.moveToNext();
        a.setId(cursor.getInt(cursor.getColumnIndex("id")));
        a.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        a.setPassword(cursor.getString(cursor.getColumnIndex("password")));

        return a;
    }

    public void addContact(int providerId, int customerId, String note) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO contacts (provider_id, customer_id, note, voted) values(?,?,?, 'no')";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, providerId);
        statement.bindDouble(2, customerId);
        statement.bindString(3, note);
        statement.execute();
        Log.i("check", "added");
    }

    public ArrayList<Contact> getContactsByProviderId(int providerId) {
        ArrayList<Contact> ary = new ArrayList<>();
        String st = "SELECT * FROM contacts WHERE provider_id = " + providerId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Contact c = new Contact();
            c.setProviderId(cursor.getInt(cursor.getColumnIndex("provider_id")));
            c.setCustomerId(cursor.getInt(cursor.getColumnIndex("customer_id")));
            c.setNote(cursor.getString(cursor.getColumnIndex("note")));

            ary.add(c);
        }
        return ary;
    }

    public ArrayList<Contact> getContactsByCustomerId(int customerId) {
        ArrayList<Contact> ary = new ArrayList<>();
        String st = "SELECT * FROM contacts WHERE customer_id = " + customerId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Contact c = new Contact();
            c.setProviderId(cursor.getInt(cursor.getColumnIndex("provider_id")));
            c.setCustomerId(cursor.getInt(cursor.getColumnIndex("customer_id")));
            c.setNote(cursor.getString(cursor.getColumnIndex("note")));

            ary.add(c);
        }
        return ary;
    }

    public Contact getContact(int providerId, int customerId){
        String st = "SELECT * FROM contacts WHERE provider_id = " + providerId + " AND customer_id = " + customerId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        Contact c = new Contact();
        c.setProviderId(cursor.getInt(cursor.getColumnIndex("provider_id")));
        c.setCustomerId(cursor.getInt(cursor.getColumnIndex("customer_id")));
        c.setNote(cursor.getString(cursor.getColumnIndex("note")));
        return c;
    }

    public void deleteContact(int providerId, int customerId) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_DELETE = "DELETE FROM contacts WHERE provider_id = " + providerId + " AND customer_id = " + customerId ;
        SQLiteStatement statement = db.compileStatement(sql_DELETE);

        statement.execute();
    }

    public boolean checkContact(int providerId, int customerId) {
        String st = "SELECT * FROM contacts WHERE provider_id = " + providerId + " AND customer_id = " + customerId;
        SQLiteDatabase db = getWritableDatabase();
        if (db.rawQuery(st, null).getCount() == 1)
            return true;
        return false;
    }

    public void updateContactNote(int providerId, int customerId, String note) {
        String st = "UPDATE contacts SET note = '" + note +"' WHERE provider_id = " + providerId + " AND customer_id = " + customerId;
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(st);
        statement.execute();
    }

    public void updateContactVote(int providerId, int customerId) {
        String st = "UPDATE contacts SET voted = 'yes' WHERE provider_id = " + providerId + " AND customer_id = " + customerId;
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(st);
        statement.execute();
    }

    public boolean checkVoted(int providerId, int customerId) {
        String st = "SELECT * FROM contacts WHERE provider_id = " + providerId + " AND customer_id = " + customerId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        String vote = cursor.getString(cursor.getColumnIndex("voted"));
        return vote.matches("yes");
    }

    public void addMeeting(int providerId, int customerId, String date) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO meetings (provider_id, customer_id, date) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, providerId);
        statement.bindDouble(2, customerId);
        statement.bindString(3, date);
        statement.execute();
    }

    public ArrayList<Meeting> getProviderMeetingsByDay(int providerId, String date) {
        ArrayList<Meeting> ary = new ArrayList<>();
        String st = "SELECT * FROM meetings WHERE provider_id = " + providerId + " AND date LIKE '%" + date.substring(0, 10) + "%' AND NOT customer_id = 0";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Meeting m = new Meeting();
            m.setProviderId(cursor.getInt(cursor.getColumnIndex("provider_id")));
            m.setCustomerId(cursor.getInt(cursor.getColumnIndex("customer_id")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));

            ary.add(m);
        }
        return ary;
    }

    public ArrayList<Meeting> getProviderEmptyMeetingsByDay(int providerId, String date) {
        ArrayList<Meeting> ary = new ArrayList<>();
        String st = "SELECT * FROM meetings WHERE provider_id = " + providerId + " AND date LIKE '%" + date.substring(0, 10) + "%' AND customer_id = 0";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Meeting m = new Meeting();
            m.setProviderId(cursor.getInt(cursor.getColumnIndex("provider_id")));
            m.setCustomerId(cursor.getInt(cursor.getColumnIndex("customer_id")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));

            ary.add(m);
        }
        return ary;
    }

    public ArrayList<Meeting> getCustomerMeetingsByDay(int customerId, String date) {
        ArrayList<Meeting> ary = new ArrayList<>();
        String st = "SELECT * FROM meetings WHERE customer_id = " + customerId + " AND date LIKE '%" + date.substring(0, 10) + "%'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Meeting m = new Meeting();
            m.setProviderId(cursor.getInt(cursor.getColumnIndex("provider_id")));
            m.setCustomerId(cursor.getInt(cursor.getColumnIndex("customer_id")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));

            ary.add(m);
        }
        return ary;
    }

    public int getCustomerIdByMeeting(int providerId, String date) {
        String st = "SELECT * FROM meetings WHERE provider_id = " + providerId + " AND  date LIKE '%" + date + "%' AND NOT customer_id = 0";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndex("customer_id"));
    }

    public int getProviderIdByMeeting(int customerId, String date) {
        String st = "SELECT * FROM meetings WHERE customer_id = " + customerId + " AND  date LIKE '%" + date + "%'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndex("provider_id"));
    }

    public boolean checkMeetings(int providerId, String date) {
        String st = "SELECT * FROM meetings WHERE provider_id = " + providerId + " AND date = '" + date + "'";
        SQLiteDatabase db = getWritableDatabase();
        if (db.rawQuery(st, null).getCount() != 0)
            return true;
        return false;
    }

    public void removeMeetings(int providerId, String date){
        SQLiteDatabase db = getWritableDatabase();
        String sql_DELETE = "DELETE FROM meetings WHERE provider_id = " + providerId + " AND customer_id = " + 0 + " AND date LIKE '%" + date + "%'";
        SQLiteStatement statement = db.compileStatement(sql_DELETE);

        statement.execute();
    }

    public void updateMeeting(int providerId, int customerId, String date){
        String st = "UPDATE meetings SET customer_id = " + customerId + " WHERE provider_id = " + providerId + " AND customer_id = 0 AND date = '" + date + "'";
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(st);
        statement.execute();
    }
}
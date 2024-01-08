package org.example;

import org.example.models.BankAccount;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Arrays.stream(args).toList().forEach(System.out::println);

        List<String> contactList = new ArrayList<>();
        contactList.add("toto");
        contactList.add("tata");
        contactList.add("tata");
        contactList.add("tete");
        String s = contactList.get(1);
        System.out.println(s);
        contactList.clear();
        System.out.println(contactList);

        System.out.println("00000000000000000000000");
        Set<String> stringSet = new HashSet<>();
        stringSet.add("AJA");
        stringSet.add("AJO");
        stringSet.add("AJE");
        stringSet.add("AJE");
        System.out.println(stringSet);

        List couleurs = new ArrayList();
        couleurs.add("Vert");
        couleurs.add("Bleu");
        couleurs.add("Orange");

        couleurs.forEach(System.out::println);

        Map mostPopularLanguagesForBigData = new HashMap();
        mostPopularLanguagesForBigData.put(1, "Python");
        mostPopularLanguagesForBigData.put(2, "R");
        mostPopularLanguagesForBigData.put(3, "Java");

        mostPopularLanguagesForBigData.forEach((key, value) -> System.out.println(key + " => " + value));

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000000000);
        bankAccount.setCurrency("CFA");
        System.out.println(bankAccount);
        System.out.println(bankAccount.hashCode());
    }
}
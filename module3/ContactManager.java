import java.util.*;

public class ContactManager {

    public static void main(String[] args) {
        HashMap<String, Contact> contacts = new HashMap<>();

        // Step 4: Add contacts here
        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1 617 555 0101"));
        contacts.put("Grace Hopper", new Contact("Grace Hopper", "+1 617 555 0202"));
        contacts.put("Alan Turing", new Contact("Alan Turing", "+1 617 555 0303"));
        contacts.put("Margaret Hamilton", new Contact("Margaret Hamilton", "+1 617 555 0404"));
        contacts.put("Tim Berners-Lee", new Contact("Tim Berners-Lee", "+1 617 555 0505"));

        // Step 5: Look up a contact
        System.out.println("=== Contact Lookup ===");
        
        // 5a. Test with a known name
        Contact found = contacts.get("Ada Lovelace");
        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println(found);
        }

        // 5b. Test with an unknown name
        Contact missing = contacts.get("John Doe");
        if (missing == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println(missing);
        }

        System.out.println(); // Blank line for readability

        // Step 6: Print sorted list
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts ===");
        for (Contact contact : sorted) {
            System.out.println(contact);
        }
    }
}

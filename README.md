# Address Book CLI

A simple command-line interface (CLI) based address book in Java. This address book allows you to add contacts with first name, last name, address, and phone number. You can search for contacts based on their phone number or name.

## Features

- Add contacts with first name, last name, address, and phone number.
- Search for contacts by phone number.
- Search for contacts by name.

## Getting Started

### Prerequisites

- Java JDK installed
- Git (optional)
  
### Example

```bash
1. Add Contact
2. Search by Phone Number
3. Search by Name
4. Exit
Choose an option: 1

Enter First Name: Zamp
Enter Last Name: test
Enter Address: 123 Main St
Enter Phone Number: 9988776655
Contact added successfully!

1. Add Contact
2. Search by Phone Number
3. Search by Name
4. Exit
Choose an option: 2

Enter Phone Number to Search: 9988776655
Search Result: Contact{firstName='Zamp', lastName='test', address='123 Main St', phoneNumber='9988776655'}
Search time: 1.23 milliseconds

1. Add Contact
2. Search by Phone Number
3. Search by Name
4. Exit
Choose an option: 3

Enter Name to Search: Zamp test
Search Result: Contact{firstName='Zamp', lastName='test', address='123 Main St', phoneNumber='9988776655'}
Search time: 0.89 milliseconds

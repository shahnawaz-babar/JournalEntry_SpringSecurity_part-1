📘 Project Development Steps


✅ Step 1: Create Journal Controller and Journal Entity
We first designed the JournalController to handle journal-related operations such as creating and retrieving journal entries.
To support this, a corresponding JournalEntry entity class was created to define the structure of a journal record.

✅ Step 2: Create User Controller and User Entity
Next, we developed the UserController to manage user-related operations.
For this, we created a User entity that represents users in the system.

✅ Step 3: Establish Relationship Between User and Journal Entry
We then established a link between the User and JournalEntry entities so that each user can have multiple journal entries.

-> This relationship was achieved using the @DBRef annotation in MongoDB.

-> It ensures that journal entries are referenced properly inside the User entity.

-> Also, when a journal entry is deleted, it is removed from both the JournalEntry collection and the corresponding user's journal list.

✅ Step 4: Add Transaction Support for Atomic Operations
To ensure data integrity and atomic operations (i.e., all-or-nothing behavior), we added transactional support using Spring’s transaction management.

-> We used the @Transactional annotation on service methods where atomicity is required.

-> To enable transactions with MongoDB, we defined a MongoTransactionManager bean in the main application class.

-> This manager is configured with a MongoDatabaseFactory parameter to handle commit and rollback operations effectively.

@Bean
public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
    return new MongoTransactionManager(dbFactory);
}
This setup ensures consistency in operations involving multiple collections like User and JournalEntry.


✅ Step 5: Connect to MongoDB Atlas (Cloud Database)
For database operations, we used MongoDB Atlas, a cloud-hosted version of MongoDB.
This allows us to store and retrieve data remotely with high availability and scalability.


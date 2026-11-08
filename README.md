Visitor Pattern — Advanced File System

Project Description

This project implements an in-memory hierarchical file system using the **Visitor Design Pattern** in Java 17.

The file system consists of files and directories. Directories can contain other directories and files, creating a tree structure.

The Visitor Pattern allows different operations to be performed on the file system without modifying the element classes every time a new operation is added.

Technologies

- Java 17
- Maven
- JUnit 5
- IntelliJ IDEA

Project Structure

```text
VisitorFileSystem/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── filesystem/
    │           ├── Element.java
    │           ├── Visitor.java
    │           ├── Node.java
    │           ├── FileNode.java
    │           ├── DirectoryNode.java
    │           ├── TotalSizeVisitor.java
    │           ├── SearchVisitor.java
    │           ├── StatisticsVisitor.java
    │           └── Main.java
    └── test/
        └── java/
            └── filesystem/
                ├── VisitorTest.java
                ├── SizeVisitorTest.java
                ├── SearchVisitorTest.java
                ├── StatisticsVisitorTest.java
                └── IntegrityTest.java
```

Implemented Features

1. File System Elements

- `Element` — common interface for file system elements.
- `Node` — abstract base class for files and directories.
- `FileNode` — represents a file and stores its size.
- `DirectoryNode` — represents a directory and stores child elements.

2. Visitor Interface

The `Visitor` interface defines two overloaded methods:

- `visit(FileNode file)`
- `visit(DirectoryNode directory)`

Each element implements the `accept(Visitor visitor)` method. This demonstrates **double dispatch**.

3. Concrete Visitors

**TotalSizeVisitor**

Calculates the total size of all files in the file system.

**SearchVisitor**

Searches for files using:
- A filename condition.
- A minimum file size.
- Both conditions must match.

Search results are returned as sorted, root-relative paths.

**StatisticsVisitor**

Calculates:
- Total number of files.
- Total number of directories.
- Maximum directory depth.

The root directory has depth 0.

Data Integrity

The implementation prevents:

- Negative file sizes.
- Duplicate sibling names.
- Adding an element that already has a parent.
- Cyclic directory structures.
- Direct modification of the internal children list.

How to Run

1. Open the project in IntelliJ IDEA.
2. Make sure Maven dependencies are loaded.
3. Open `Main.java`.
4. Run the `main()` method.

You can also run the tests from the project root:

```bash
mvn test
```

Sample Output

```text
===== FILE SYSTEM =====

===== TOTAL SIZE =====
Total size: 11500 bytes

===== SEARCH RESULTS =====
Photos/photo2.png

===== STATISTICS =====
Files: 5
Directories: 4
Maximum depth: 2
```

Testing

The project contains 18 JUnit 5 tests.

| Test Class | Number of Tests |
|---|---:|
| IntegrityTest | 6 |
| SearchVisitorTest | 4 |
| SizeVisitorTest | 3 |
| StatisticsVisitorTest | 3 |
| VisitorTest | 2 |
| **Total** | **18** |

Test results:

- Tests run: 18
- Failures: 0
- Errors: 0
- Skipped: 0
- Build result: **BUILD SUCCESS**

Design Pattern Benefits

The Visitor Pattern separates operations from the file system elements.

It provides:
- Separation of responsibilities.
- Easier addition of new operations.
- Cleaner element classes.
- Double dispatch.

A new visitor can be added without changing `FileNode` or `DirectoryNode`.

However, adding a new element type requires updating the Visitor interface and its implementations.

Author

Student: Nazym Kulchikova

GitHub Repository

https://github.com/knazymb/VisitorPattern.git

# LibMan — Library Management System

A Java-based library management system built as a CSE212 course assignment. LibMan supports managing books and online articles, creating member accounts, checking out/returning materials, and tracking overdue charges — all through a GUI (Swing) interface.

---

## Features

- **Book Management** — Add books with ISBN-13 validation, check them out with a due date, and return them
- **Online Article Management** — Add articles with DOI validation, grant/revoke member access
- **Member Accounts** — Create Regular, Student, and Academic member accounts (each with different checkout limits)
- **Overdue Tracking** — Calculates per-day overdue fees; lists all members sorted by total overdue amount (descending)
- **GUI Interface** — Swing-based graphical front-end (`MainFrame`, form dialogs per operation)
- **Console Fallback** — A fully functional console/menu-driven interface also exists (`Menu.java`)

---

## Project Structure

```
CSE212_Assignment/
├── src/
│   ├── LibMan.java                          # Entry point (launches MainFrame)
│   ├── MainFrame.java                       # Main Swing window
│   ├── Menu.java                            # Console-based menu (alternative UI)
│   │
│   ├── Book.java                            # Book entity + ISBN-13 validator
│   ├── OnlineArticle.java                   # Online article entity + DOI validator
│   ├── LibraryMaterial.java                 # Abstract base for library materials
│   ├── LibraryData.java                     # Interface (calculateCost)
│   │
│   ├── RegularMember.java                   # Base member class (limit: 1 book, 1 article)
│   ├── Student.java                         # Student member (extended limits)
│   ├── Academic.java                        # Academic member (extended limits)
│   │
│   ├── Date.java                            # Date utility + overdue day calculation
│   │
│   ├── AddNewBookFrame.java                 # GUI: Add book dialog
│   ├── AddNewOnlineArticleFrame.java        # GUI: Add article dialog
│   ├── CreateMemberAccountFrame.java        # GUI: Create member dialog
│   ├── CheckOutBookFrame.java               # GUI: Check out book dialog
│   ├── ReturnBookFrame.java                 # GUI: Return book dialog
│   ├── GiveAccessToOnlineArticleFrame.java  # GUI: Grant article access dialog
│   ├── RevokeAccessToOnlineArticleFrame.java# GUI: Revoke article access dialog
│   ├── Popup.java                           # Reusable popup/dialog utility
│   │
│   ├── ISBNMismatchException.java           # Custom exception for invalid ISBNs
│   └── NotValidDateException.java           # Custom exception for invalid dates
└── README.md
```

---

## Member Types & Limits

| Type        | Book Limit | Article Limit | Overdue Fee      |
|-------------|-----------|---------------|------------------|
| Regular     | 1         | 1             | 50 TRL / day     |
| Student     | (higher)  | (higher)      | 50 TRL / day     |
| Academic    | (higher)  | (higher)      | 50 TRL / day     |

> Exact limits for Student and Academic are defined in their respective subclasses.

---

## How to Run

### Prerequisites
- Java 8 or higher
- An IDE such as Eclipse (the project includes `.classpath` and `.project` files) or any standard Java environment

### From Eclipse
1. Import the project: **File → Import → Existing Projects into Workspace**
2. Select the `CSE212_Assignment` directory
3. Run `LibMan.java` as a Java Application

### From the Command Line
```bash
cd CSE212_Assignment/src
javac *.java
java LibMan
```

---

## Notes

- Data is **not persisted** between sessions (in-memory only)
- ISBN validation uses the standard **ISBN-13** checksum algorithm
- The console interface (`Menu.start()`) is still available but commented out in `LibMan.java`
- Currency is denominated in **TRL (Turkish Lira)**

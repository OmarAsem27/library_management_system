# Library Management System — Technical & Architectural Documentation

A console-based Java application for managing library operations, members, books, and loans.

---

## 📌 Development Lifecycle & Process Phases

- **Phase 1:** Understand Problem
- **Phase 2:** Discover Domain
- **Phase 3:** Find Responsibilities
- **Phase 4:** Design Objects
- **Phase 5:** Design Collaborations
- **Phase 6:** Design Storage
- **Phase 7:** Design Use Cases
- **Phase 8:** Implement
- **Phase 9:** Refactor

---

## 🏗️ Responsibility Model

### Application
- Show menu
- Collect input
- Request operations
- Present results

### Member
- Maintain a valid state
- Maintain knowledge of its loans
- Provide information about its borrowing activity

### Library
- Manage books
- Manage members
- Manage loans
- Coordinate borrowing operations
- Coordinate return operations
- Locate books
- Locate members
- Enforce borrowing policy
- Enforce library-level policies

### Book
- Maintain a valid state
- Protect availability rules
- Become borrowed
- Become available

### Loan
- Represent the borrowing relationship
- Maintain loan lifecycle state
- Know whether it is active or completed
- Know the borrowing participant (Member)
- Know the borrowed item (Book)
- Protect loan state transitions

---

## 🏷️ Objects Properties

### Member
- `id`
- `name`
- `active/inactive`

### Book
- `id`
- `title`
- `available/unavailable`

### Loan
- `member`
- `book`
- `startDate`
- `endDate`
- `active/inactive`

---

## 🔗 Relationship Model

### Library
- Owns Members
- Owns Books
- Owns Loans

### Member
- References Loans

### Loan
- References Member
- References Book

### Book
- Maintains its own state

---

## 📜 Important Business Rules

- A Book can have only one active Loan.
- A Loan cannot exist without a Member.
- A Loan cannot exist without a Book.
- Library coordinates borrowing.
- Book protects availability.
- Loan protects lifecycle.
- Library enforces maximum active loans policy.
- Member or Book with active loans cannot be removed.
- Maximum active loans per member = 5

---

## 📋 System Use Cases Overview

- Register Member
- Register Book
- Borrow Book
- Return Book
- Show Active Loans
- Update Member
- Update Book
- Deactivate Member
- Retire Book

---

## 🔄 Detailed Use Case Workflows

### 1. Register Member Use Case

1. Application shows menu.
2. User chooses Register Member.
3. Application collects member information.
4. Application asks Library to register the member.
5. Library creates the Member.
6. Member establishes its valid state.
7. Library stores the Member.
8. Library communicates the result.
9. Application presents the result.

---

### 2. Register Book Use Case

1. Application shows menu.
2. User chooses Register Book.
3. Application collects Book information.
4. Application asks Library to register the Book.
5. Library creates the Book.
6. Book establishes a valid initial state.
7. Book starts as available.
8. Library stores the Book.
9. Library communicates the result.
10. Application presents the result.

---

### 3. Borrow Book Use Case

1. Application shows menu.
2. User chooses Borrow Book.
3. Application collects:
   - Member ID
   - Book ID
4. Application requests borrowing from Library.
5. Library locates the Member.
6. Library verifies the Member is eligible to borrow.
7. Library locates the Book.
8. Library verifies the Book is available.
9. Library creates a Loan connecting the Member and Book.
10. Loan establishes a valid initial state.
11. Book becomes unavailable.
12. Library stores the Loan.
13. Library communicates the result.
14. Application presents the result.

---

### 4. Return Book Use Case

1. Application shows menu.
2. User chooses Return Book.
3. Application collects:
   - Member ID
   - Book ID
4. Application requests return from Library.
5. Library locates the Member.
6. Library locates the active Loan for the Member and Book.
7. Library requests the Loan to complete itself.
8. Loan completes its lifecycle and records the return date.
9. Book becomes available.
10. Library communicates the result.
11. Application presents the result.

---

### 5. Show Active Loans Use Case

1. Application shows menu.
2. User chooses Show Active Loans.
3. Application requests active loans from Library.
4. Library locates all active Loans.
5. Library communicates the result.
6. Application presents the result.

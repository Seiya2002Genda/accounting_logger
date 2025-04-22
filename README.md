# 📘 Accounting Logger App

This is a simple **Java Swing-based desktop application** designed for personal accounting. Users can log their **income** and **expenses**, add **comments**, and save data to a **CSV file**. The interface supports a custom **background image** and intuitive layout for quick financial tracking.

---

## 📷 Preview

> *(You can include a screenshot like: `![screenshot](screenshot.png)` if available)*

---

## ✅ Features

- 🔹 Input fields for **item** and **amount**
- 🔺 Toggle between **Income** and **Expense**
- 💬 Optional **comment section** (memo)
- 💾 Save records to `accounting_log.csv`
- 📂 Load and view CSV content directly from the app
- 🖼️ Supports custom background image (optional)

---

## 🚀 Getting Started

### Requirements

- Java 8 or higher
- IntelliJ IDEA (or any IDE that supports Java)
- `Accounting/article_scheme.jpeg` *(optional background image file)*

---

### 🔧 How to Run

1. **Clone this repository**:
   ```bash
   git clone https://github.com/your-username/accounting-logger-app.git
   ```

2. **Open the project** in IntelliJ IDEA or any Java IDE.

3. **Run** the `Main_logger.java` file.

4. *(Optional)* Set a background image:
   - Place your image in `Accounting/article_scheme.jpeg`
   - Update the line in `Main_logger.java`:
     ```java
     private static final String BACKGROUND_IMAGE_PATH = "Accounting/article_scheme.jpeg";
     ```

---

## 📄 CSV Format

Each entry is saved in `accounting_log.csv` in the following format:

```
Item,Amount,Type (Income/Expense),Comment
Salary,5000,Income,Monthly payment
Coffee,-5,Expense,Morning coffee
```

---

## 🛠️ Project Structure

```
├── Accouting_logger/
│   └── Main_logger.java
├── Accounting/
│   └── article_scheme.jpeg   ← optional background image
├── accounting_log.csv        ← generated automatically
└── README.md
```

---

## 📌 To Do / Future Features

- [ ] Add date/time tracking  
- [ ] Visual summary (chart for monthly income/expense)  
- [ ] Delete or edit CSV entries  
- [ ] Export as PDF  

---

## 📃 License

**MIT License**

# 📘 Accounting Logger App

This is a simple **Java Swing-based desktop application** designed for personal accounting. Users can log their **income** and **expenses**, add **comments**, and save data to a **CSV file**. The interface supports a custom **background image** and intuitive layout for quick financial tracking.

---

## 📷 Preview

> <img width="734" alt="Screenshot 0007-04-22 at 13 20 52" src="https://github.com/user-attachments/assets/3d2b7508-40c7-490e-a0f3-8c4bba95e600" />


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
- *(optional background image file)*
  
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
│   └── No name   ← optional background image
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

# 🎓 UniManager

Academic event management system with support for multiple types of participants and events, terminal interface (CLI) and export of certificates in PDF with iText.

---

## 📦 Technologies Used

- **Java 17**
- **Maven**
- **iText 7** (External library {for exporting certificates in PDF format} )
- **Clean Architecture** (Domain, UseCases, Infrastructure, View)

---

### Folder Structure

src/
└── main/
    └── java/
       └── br/edu/ifba/inf008/uniManager/
           ├── domain/
           │   ├── entities/            # Domain Entities (Event, Participant, etc.)
           │   ├── ports/               # Repository and service interfaces
           ├── infra/
           │   ├── repository/          # Repository implementations (.dat)
           │   └── exports/             # Export implementations (PDF)
           ├── useCase/                 # Business rules and orchestration
           ├── view/
           │   └── cli/                 # UI (Command-Line Interface)
           ├── utils/                   # Utils (menus, validators, exceptioins)
           └── App.java                 # (main)
    

files/
├── binaries/
│   └── events.dat                      # Persistence of serialized events
│   └── participants.dat                # Persistence of serialized participants
└── reports/
    └── *.pdf                           # Reports in Pdf

## 🚀 How to Run the Project

### ✅ Requirements

- Java **17 or superior**
- Maven **3.6+**
- Terminal with UTF-8 support and preferably ANSI (for colors in CLI, and clear lines)

### ⚙️ Clonar o projeto

git clone https://github.com/uKuroo/uniManager.git
cd uniManager

### ✅ Run with

mvn clean package && mvn exec:java

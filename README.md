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

```plaintext
src/
└── main/
    └── java/
        └── br/
            └── edu/
                └── ifba/
                    └── inf008/
                        └── uniManager/
                            ├── domain/
                            │   ├── entities/
                            │   └── ports/
                            ├── infra/
                            │   ├── repository/
                            │   └── exports/
                            ├── useCase/
                            ├── utils/
                            ├── view/
                            │   └── cli/
                            └── App.java

files/
├── binaries/    # Arquivos .dat (eventos e participantes)
└── reports/     # Certificados exportados em PDF
```

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

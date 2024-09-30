package com.swiftlend.agiospring.domain.database;

import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.application.model.Installment;
import com.swiftlend.agiospring.domain.application.model.Loan;
import com.swiftlend.agiospring.domain.application.repository.ContactRepository;
import com.swiftlend.agiospring.domain.application.repository.InstallmentRepository;
import com.swiftlend.agiospring.domain.application.repository.LoanRepository;
import com.swiftlend.agiospring.domain.application.service.impl.InstallmentServiceImpl;
import com.swiftlend.agiospring.domain.security.model.User;
import com.swiftlend.agiospring.domain.security.repository.UserRepository;
import com.swiftlend.agiospring.domain.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public void run(String... args) throws Exception {
        // 1. Criar um único usuário utilizando a lógica de registro
        Optional<User> existingUser = userRepository.findByUsername("admin");

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setUsername("admin");
            newUser.setPassword(passwordEncoder.encode("admin123"));
            newUser.setEmail("admin@example.com");
            userRepository.save(newUser);

            String token = tokenService.genererateToken(newUser);
            System.out.println("Usuário administrador criado com sucesso. Token: " + token);
        } else {
            System.out.println("Usuário administrador já existe. Pulando criação.");
        }

        // Obter o usuário administrador para associar aos contatos e empréstimos
        User user = userRepository.findByUsername("admin").orElseThrow(() -> new RuntimeException("Admin user not found"));

        // 2. Criar 10.000 contatos
        List<Contact> contacts = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Contact contact = new Contact();
            contact.setFirstName("FirstName" + i);
            contact.setLastName("LastName" + i);
            contact.setCpf("CPF" + i);
            contact.setEmail("email" + i + "@example.com");
            contact.setPhone("123456789" + i);
            contact.setLastUpdate(LocalDateTime.now());
            contact.setOwnerUser(user);
            contacts.add(contact);

            // Persistir os contatos a cada 1000 para evitar sobrecarregar a memória
            if (i % 100 == 0) {
                contactRepository.saveAll(contacts);
                contacts.clear();
            }
        }
        contactRepository.saveAll(contacts);

        // 3. Criar 10.000 empréstimos, cada um vinculado a um contato
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            Loan loan = new Loan();
            loan.setAmount(1000f + random.nextFloat() * 9000f); // Valor aleatório entre 1000 e 10000
            loan.setLoan_date(LocalDateTime.now());
            loan.setTotalInstallments(5 + random.nextInt(20)); // Número de parcelas entre 5 e 25
            loan.setInstallmentInterval(30); // Intervalo de 30 dias entre as parcelas
            loan.setOwner(contactRepository.findById((long) i).orElse(null));// Associando ao contato pelo ID
            loan.setLastUpdate(LocalDateTime.now());

            // Salvar o empréstimo para obter um ID gerado
            Loan savedLoan = loanRepository.save(loan);

            // Criar as parcelas para o empréstimo salvo
            createEachInstallment(savedLoan, user);
        }

        System.out.println("Banco de dados inicializado com sucesso!");
    }

    // Método para criar parcelas usando o usuário admin
    private void createEachInstallment(Loan loan, User user) {
        Float amountInstallment = loan.getAmount() / loan.getTotalInstallments();
        for (int i = 1; i <= loan.getTotalInstallments(); i++) {
            Installment installment = new Installment();
            installment.setLastUpdate(LocalDateTime.now());
            installment.setOwner(user.getUsername()); // Definindo o username do usuário como proprietário
            InstallmentServiceImpl.createOneinstallment(loan, amountInstallment, user, i, installment, installmentRepository);
        }
    }
}



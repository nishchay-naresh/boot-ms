package com.nish.ms.repo;

import com.nish.ms.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
 * Spring boot – CommandLineRunner interface
 *
 * CommandLineRunner is a Spring Boot callback interface that
 * Allows you to execute code once after the Spring application context has been fully initialized and just before the application finishes startup.
 *
 * It is commonly used for:
 *   Initializing data
 *   Running startup logic
 *   Validating configuration
 *   Starting background tasks
 *   Logging application information
 *
 *	Spring Boot starts
 *		↓
 *	Creates all beans
 *		↓
 *	Dependency Injection completes
 *		↓
 *	ApplicationContext is ready
 *		↓
 *	run() executes
 *		↓
 *	Application is fully started
 *
 *
 * https://www.baeldung.com/spring-boot-console-app
 * */
@Component
public class CustomerRepoUsingCLRunner implements CommandLineRunner {

    @Autowired
    private CustomerRepoIface customerRepo;

    @Override
    public void run(String... args) throws Exception {

        customerRepo.save(new Customer(201, "Rachel", "rachel@abc.com", 81111111));
        customerRepo.save(new Customer(202, "Tressa", "tressa@abc.com", 82222222));
        customerRepo.save(new Customer(203, "Kevin", "kevin@abc.com", 83333333));
        customerRepo.save(new Customer(204, "Smith", "smith@abc.com", 84444444));
        customerRepo.save(new Customer(205, "Tony", "tony@abc.com", 84555555));

    }
}

package com.william.Employee.Expense.and.Reimbursement.employee.controller;


import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpenseDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNewExpenseNotification(ExpenseDto movieDto) {
        String message = "A new Expense was submitted: " + movieDto.getNameOfEmployee();
        messagingTemplate.convertAndSend("/topic/newMovie", message);
    }
}



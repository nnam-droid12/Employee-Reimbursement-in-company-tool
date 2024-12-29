package com.william.Employee.Expense.and.Reimbursement.employee.controller;


import io.swagger.v3.oas.annotations.Operation;
import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpenseDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Operation(
            summary = "Get WebSocket Info",
            description = "To receive notifications for new expenses:\n\n" +
                    "1. Connect to WebSocket at: http://ec2-51-20-93-163.eu-north-1.compute.amazonaws.com:5050/ws**\n" +
                    "2. Subscribe to the topic: **/topic/newExpense**\n" +
                    "3. Frontend will receive new expense notifications via this subscription."
    )
    @GetMapping("/ws")
    public String getWebSocketInfo() {
        return "Connect to WebSocket at /ws and subscribe to /topic/newExpense to get notifications.";
    }

    public void sendNewExpenseNotification(ExpenseDto expenseDto) {
        String message = "A new Expense was submitted: " + expenseDto.getNameOfEmployee();
        messagingTemplate.convertAndSend("/topic/newExpense", message);
    }
}



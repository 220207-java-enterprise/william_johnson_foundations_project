package com.revature.erm.dtos.requests;

import com.revature.erm.models.ReimbursementStatus;
import com.revature.erm.models.User;

import java.time.LocalDateTime;

public class UpdateReimbursementRequest {

    private User resolver;
    private ReimbursementStatus status;
    private LocalDateTime resolved;
}

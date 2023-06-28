package org.example.repositories;

import java.util.Date;

public record Transaction(
        int id,
        String take,
        String delivery,
        Date date,
        String note
) {
}

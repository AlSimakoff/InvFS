package org.example.repositories;

import java.util.Date;

public record Transaction(
        int id,
        String from_take,
        String to_delivery,
        String date_delivery,
        String note
) {
}

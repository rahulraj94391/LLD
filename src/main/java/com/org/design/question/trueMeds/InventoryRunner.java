package com.org.design.question.trueMeds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryRunner {
    public static void main(String[] args) {

    }
}

class Medicine {
    private final ArrayList<Batch> batches = new ArrayList<>();
    String medicineID;
    String medicineName;

    public Medicine(String medicineID) {
        this.medicineID = medicineID;
    }

    public ArrayList<Batch> getBatches() {
        return batches;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}

class Batch {
    String batchID;
    int quantity;
    LocalDate expiryDate;

    public Batch(String batchID, int quantity, LocalDate expiryDate) {
        this.batchID = batchID;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }
}

class InventoryService {
    // medicineId vs Batch
    private HashMap<String, Medicine> inventory = new HashMap<>();

    private int firstValidBatch(List<Batch> batches, LocalDate date) {
        int l = 0, r = batches.size() - 1, ans = -1;

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (batches.get(m).expiryDate.isAfter(date)) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public void addNewBatch(String medicineId, String batchId, int qty, LocalDate expiry) {
        Medicine med = null;
        if (!inventory.containsKey(medicineId)) {
            inventory.put(medicineId, new Medicine(medicineId));
        }
        med = inventory.get(medicineId);
        List<Batch> batches = med.getBatches();

        int idx = firstValidBatch(batches, expiry);
        Batch newBatch = new Batch(batchId, qty, expiry);

        if (idx == -1) {
            batches.add(newBatch);
        } else {
            batches.add(idx, newBatch);
        }
    }

    public int getStockLevel(String medicineId, LocalDate date) {
        Medicine med = inventory.get(medicineId);
        if (med == null) return 0;

        List<Batch> batches = med.getBatches();
        int idx = firstValidBatch(batches, date);
        if (idx == -1) return 0;

        int total = 0;
        for (int i = idx; i < batches.size(); i++) {
            total += batches.get(i).quantity;
        }
        return total;
    }

    public boolean fulfillOrder(String medicineId, int qty, LocalDate orderDate) {
        Medicine med = inventory.get(medicineId);
        if (med == null) return false;

        List<Batch> batches = med.getBatches();
        int idx = firstValidBatch(batches, orderDate);
        if (idx == -1) return false;

        // Check availability
        int available = 0;
        for (int i = idx; i < batches.size(); i++) {
            available += batches.get(i).quantity;
        }
        if (available < qty) return false;

        // Deduct using FEFO
        int remaining = qty;
        for (int i = idx; i < batches.size() && remaining > 0; i++) {
            Batch b = batches.get(i);
            int used = Math.min(b.quantity, remaining);
            b.quantity -= used;
            remaining -= used;
        }

        return true;
    }
}









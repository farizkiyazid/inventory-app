package com.example.inventoryapp.controller;

import com.example.inventoryapp.mapper.ItemMapper;
import com.example.inventoryapp.mapper.InventoryAllocMapper;
import com.example.inventoryapp.mapper.StaffMapper;
import com.example.inventoryapp.model.InventoryAlloc;
import com.example.inventoryapp.model.Item;
import com.example.inventoryapp.model.Staff;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("api/item")
public class InventoryAppController {
    private StaffMapper staffMapper;
    private ItemMapper itemMapper;
    private InventoryAllocMapper allocMapper;

    public InventoryAppController(StaffMapper staffMapper, ItemMapper itemMapper, InventoryAllocMapper allocMapper){
        this.staffMapper = staffMapper;
        this.itemMapper = itemMapper;
        this.allocMapper = allocMapper;
    }

    @GetMapping("/all")
    public List<Item> getAll(){
        return itemMapper.getAll();
    }

    @GetMapping()
    public Item getOne(@RequestParam String idItem){
        return itemMapper.getById(Integer.parseInt(idItem));
    }

    // ADD ITEM
    @PostMapping()
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        itemMapper.insert(item);
        Item inserted = itemMapper.getById(item.getIdItem());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newItem", inserted);
        jsonObject.put("status", "Item inserted successfully");
        return new ResponseEntity<>(jsonObject, HttpStatus.CREATED);
    }

    // EDIT ITEM
    @PutMapping()
    public ResponseEntity<?> editItem(@RequestBody Item item) {
        itemMapper.update(item);
        Item edited = itemMapper.getById(item.getIdItem());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("editedItem", edited);
        jsonObject.put("status", "Edited item saved successfully");
        return new ResponseEntity<>(jsonObject, HttpStatus.ACCEPTED);
    }

    // DELETE ITEM
    @DeleteMapping()
    public ResponseEntity<?> deleteItem(@RequestBody Item item) {
        Item selected = itemMapper.getById(item.getIdItem());
        if (selected == null) {
            return new ResponseEntity<>("Item with ID " + item.getIdItem()  + " not found", HttpStatus.NOT_FOUND);
        }
        itemMapper.delete(item.getIdItem());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "Item deleted successfully");
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    // ASSIGN/ALLOCATE INVENTORY TO STAFF
    @PostMapping("/assignItem")
    public ResponseEntity<?> assignItem(@RequestBody InventoryAlloc alloc) throws IOException, TimeoutException {
        // check Inventory and staff availability
        if(itemMapper.getById(alloc.getIdItem()) == null){
            return new ResponseEntity<>("Inventory with ID " + alloc.getIdItem()  + " not found", HttpStatus.NOT_FOUND);
        } else if (!itemMapper.getById(alloc.getIdItem()).isAvailable()){
            return new ResponseEntity<>("Inventory with ID " + alloc.getIdItem()  + " not available", HttpStatus.NOT_FOUND);
        } else if (staffMapper.getById(alloc.getIdStaff()) == null){
            return new ResponseEntity<>("Staff with ID " + alloc.getIdStaff()  + " not found", HttpStatus.NOT_FOUND);
        }

        // insert to invAlloc table
        allocMapper.insert(alloc);

        // update availability in inventory table.
        Item thisItem = itemMapper.getById(alloc.getIdItem());
        thisItem.setAvailable(false);
        itemMapper.update(thisItem);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "Item allocated to user successfully");
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    // SHOW REPORT BY STAFF
    @GetMapping("invReport/staff")
    public ResponseEntity<?> getInventoryReportByStaff(){
        JSONObject jsonObject = new JSONObject();
        List<Staff> staffList = staffMapper.getAll();
        JSONArray arrReport = new JSONArray();
        for (Staff s : staffList){
            JSONObject jsonReport = new JSONObject();
            jsonReport.put("staff", s.toJsonObject());
            // get the items
            JSONArray arrItems = new JSONArray();
            List<InventoryAlloc> allocs = allocMapper.getAllByStaff(s.getIdStaff());
            for(InventoryAlloc alloc : allocs){
                Item item = itemMapper.getById(alloc.getIdItem());
                arrItems.add(item.toJsonObject());
            }
            jsonReport.put("items",arrItems);
            arrReport.add(jsonReport);
        }
        jsonObject.put("allInvReportByStaff", arrReport);
        jsonObject.put("status", "Retrieval success");
        return new ResponseEntity<>(jsonObject, HttpStatus.FOUND);
    }

    // SHOW REPORT BY ITEM
    @GetMapping("invReport/item")
    public ResponseEntity<?> getInventoryReportByItem(){
        JSONObject jsonObject = new JSONObject();
        List<Item> itemList = itemMapper.getAll();
        JSONArray arrReport = new JSONArray();
        for (Item it : itemList){
            JSONObject jsonReport = new JSONObject();
            jsonReport.put("item", it.toJsonObject());
            // get the staff.. Karena satu item cuman bisa di satu staff, disini tidak perlu array
            InventoryAlloc alloc = allocMapper.getByItem(it.getIdItem());
            if (alloc != null){
                Staff staff = staffMapper.getById(alloc.getIdStaff());
                jsonReport.put("staff",staff.toJsonObject());
                arrReport.add(jsonReport);
            } else {
                jsonReport.put("staff", "not assigned yet");
                arrReport.add(jsonReport);
            }
        }
        jsonObject.put("allInvReportByItem", arrReport);
        jsonObject.put("status", "Retrieval success");
        return new ResponseEntity<>(jsonObject, HttpStatus.FOUND);
    }
}

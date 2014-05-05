/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = initRequestForm;

            function initRequestForm() {
                var transferRequestForm = document.getElementById("transferRequestForm");
                var from = document.getElementById("fromAccount");
                var to = document.getElementById("toAccount");
                var amount = document.getElementById("transferAmount");
                var sendOn = document.getElementById("sendOn");
                var activeDate = document.getElementById("activeDate");
                
                var today = new Date();
                activeDate.value = (today.getMonth()+1) + "/" + today.getDate() + "/" + today.getFullYear();
                activeDate.readOnly = true;
                
                sendOn.onchange = function() {
                    if (sendOn.selectedIndex > 0) {
                        activeDate.readOnly = false;
                    } else {
                        var today = new Date();
                        activeDate.value = (today.getMonth()+1) + "/" + today.getDate() + "/" + today.getFullYear();
                        activeDate.readOnly = true;
                        
                    }
                }
                
                transferRequestForm.onsubmit = function() {
                    if (from.selectedIndex <= 0 || to.selectedIndex <= 0) {
                        alert("Please select a valid account.");
                        return false;
                    }
                    
                    if (from.selectedIndex == to.selectedIndex) {
                        alert("Please select different accounts.");
                        return false;
                    }
                    
                    var amt = parseFloat(amount.value);
                    if (!amt) {
                        alert('Please input a valid amount.');
                        return false;
                    }
                    if (amt <= 0) {
                        alert('Please input a positive amount.');
                        return false;
                    }
                }
            }




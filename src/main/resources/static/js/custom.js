
function setLocal(id) {
    localStorage.removeItem("id");
    localStorage.setItem("id", JSON.stringify(id));
}
function saveGadget() {


    var name = document.getElementById("name").value;
    var serialNumber = document.getElementById("serial").value;
    var description = document.getElementById("description").value;
    var batterCapacity = document.getElementById("capacity").value;
    var location = document.getElementById("location").value;
    var model = document.getElementById("model").value;


    if (name === "" || serialNumber === "" || description === "" || batterCapacity === "" || location === "" || model === "") {
        alert("Please fill in All Details")
        return;
    }


    var gadget_image = $('#gadgetImage')[0].files[0];


    var gadgetImageValue = document.getElementById("gadgetImage");

    if (gadgetImageValue.value.toString().length === 0) {
        alert("Please upload Gadget Image", "danger");
        return
    }


    var data = new FormData();
    data.append('gadget_image', gadget_image);


    var jsonDataObj = {
        "name": name,
        "serialNumber": serialNumber,
        "batteryLevel": batterCapacity,
        "description": description,
        "status": "NotAllocated",
        "model": model
    }

    data.append("jsonData", JSON.stringify(jsonDataObj));

    $.ajax({
        type: "POST",
        url: "saveGadget/" + location,
        data: data,
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            alert(data.message);
            window.location.href = "/";
        },
        error: function (e) {
            console.log(e.message);
        }

    })


}


function saveLocation() {
    var district = document.getElementById("district").value;
    var province = document.getElementById("province").value;

    var jsonDataObj = {
        "districtName": district,
        "province": province,
    }


    $.ajax({
        type: "POST",
        url: "saveLocation",
        data: JSON.stringify(jsonDataObj),
        enctype: "application/json",
        processData: false,
        contentType: "application/json",
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            alert(data.toString());
        },
        error: function (e) {
            console.log(e.message);
        }

    })


}

function saveEmployee() {


    var employeeId = document.getElementById("employeeId").value;
    var employeeFirstName = document.getElementById("employeeFirstName").value;
    var employeeLastName = document.getElementById("employeeLastName").value;
    var employeeUserName = document.getElementById("employeeUserName").value;


    if (employeeId === "" || employeeFirstName === "" || employeeLastName === "" || employeeUserName === "") {
        alert("Please fill in All Details")
        return;
    }


    var jsonDataObj = {
        "employeeId": employeeId,
        "employeeFirstName": employeeFirstName,
        "employeeLastName": employeeLastName,
        "employeeUserName": employeeUserName
    }


    $.ajax({
        type: "POST",
        url: "saveEmployee",
        data: JSON.stringify(jsonDataObj),
        enctype: "application/json",
        processData: false,
        contentType: "application/json",
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            alert(data);
        },
        error: function (e) {
            console.log(e.message);
        }

    })


}

function getLocations() {


    $.ajax({
        type: "GET",
        url: "/getAllLocations",

        success: function (data) {

            var select = $("#location");
            select.empty();
            select.append(`<option value="" selected>Select Location </option>`);
            for (var i = 0; i < data.length; i++) {
                select.append(`<option value="${data[i].id}">${data[i].districtName} | ${data[i].province}</option>`);
            }

        }
    })

}

function getGadgetForView() {
    var id = JSON.parse(localStorage.getItem("id"));
    var allocationDate = "";

    var allocationData = $("#allocationData");
    allocationData.empty();

    $.ajax({
        type: "GET",
        url: "getGadget/" + id,

        success: function (data) {
            console.log(data)
            allocationDate = data.dateIssuedToEmployee;
            //#id

            var gadgetData = $("#GadgetData");
            gadgetData.empty();

            gadgetData.append(`<div class="col-md-4" style="font-size: 15px;">
               
                <p class="my-1 py-1" id="name">Name : ${data.name}</p>
                <p class="my-1 py-1" id="serial">Serial # : ${data.serialNumber}</p>
                <hr>
                    <p class="my-1 py-1" style="font-weight: bold">Description :</p>
                    <p class="my-1 py-1"> ${data.description}</p>
            </div>
            <!-- Billing Address -->    
            <div class="col-md-4" style="font-size: 15px;">
    
                <p class="my-1 py-1" id="model">Model : ${data.model}</p>
                <p class="my-1 py-1" id="battery">Battery Capacity : ${data.batteryLevel}%</p>
                <p class="my-1 py-1" id="status">Status : ${data.status}</p>
                 
            </div>
            <!-- Status -->
            <div class="col-md-4 text-center" style="font-size: 15px;">
                <p class="font-weight-bold my-0 py-0">Gadget Image</p>
                
                <img src="/uploads/gadget/${data.serialNumber}/${data.image}" class="img-fluid img-thumbnail"
                     alt="No Image">
            </div>`);



            $.ajax({
                type: "GET",
                url: "getAllocation/" + id,

                success: function (data) {
                    console.log(data)


                    if (data === "") {
                        console.log("No Allocation");

                        allocationData.append(`<div class="col-md-4" style="font-size: 15px;">
                    <p class="my-1 py-1" id="employee">Not Allocated</p>
                  
                </div>`);
                    } else {

                        allocationData.append(`<div class="col-md-4" style="font-size: 15px;">
                    <p class="my-1 py-1" id="employee">Employee : ${data.employeeFirstName} ${data.employeeLastName}</p>
                    <p class="my-1 py-1" id="date">Date : ${allocationDate}</p>
                    <p class="my-1 py-1" id="status">Status : Allocated</p>
                </div>`);
                    }


                },
                error: function (error) {

                }

            });

            $.ajax({
                type: "GET",
                url: "GetMaintananceHistory/" + id,

                success: function (data) {
                    console.log(data)

                    $("#maintananceHistory").DataTable({
                        data: data,
                        columns: [
                            {
                                "data": function (row) {
                                    return row.dateTime.substring(0,10) +" "+row.dateTime.substring(11,16);

                                }
                            },
                            {
                                "data": function (row) {
                                    return row.title;

                                }
                            },
                            {
                                "data": function (row) {
                                    return row.description;
                                }
                            },


                        ]
                    });



                },
                error: function (error) {

                }

            });






        },
        error: function (error) {

        }

    });



}

function getGadgetsForMaintanance() {

    console.log("Loading Gadgets");

    $.ajax({
        type: "GET",
        url: "getAllGadgets",

        success: function (data) {

            console.log(data);
            var gadgetSelect = $("#gadget");
            gadgetSelect.empty();
            gadgetSelect.append(`<option value="" selected>Select Gadget </option>`);
            for (var i = 0; i < data.length; i++) {
                gadgetSelect.append(`<option value="${data[i].serialNumber}">${data[i].name} | ${data[i].location.districtName}</option>`);
            }

        }
    })



}

function saveMaintanance() {

    var gadget = document.getElementById("gadget").value;
    var title = document.getElementById("title").value;
    var description = document.getElementById("description").value;
    if (gadget === ""){
        alert("Please Select Gadget");
        return;
    }

    if (  title === "" || description === "") {
        alert("Please fill in All Details")
        return;
    }

    var jsonDataObj = {
        "title": title,
        "description":description
    }

    $.ajax({
        type: "POST",
        url: "saveMaintanance/"+gadget,
        data: JSON.stringify(jsonDataObj),
        enctype: "application/json",
        processData: false,
        contentType: "application/json",
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            alert(data);
        },
    })

}
function getGadget() {
    var id = JSON.parse(localStorage.getItem("id"));

    $.ajax({
        type: "GET",
        url: "getGadget/" + id,

        success: function (data) {
            console.log(data)
            //#id

            var gadgetData = $("#GadgetData");
            gadgetData.empty();

            gadgetData.append(`<div class="col-md-4" style="font-size: 15px;">
               
                <p class="my-1 py-1" id="name">Name : ${data.name}</p>
                <p class="my-1 py-1" id="serial">Serial # : ${data.serialNumber}</p>
                <hr>
                    <p class="my-1 py-1" style="font-weight: bold">Description :</p>
                    <p class="my-1 py-1"> ${data.description}</p>
            </div>
            <!-- Billing Address -->    
            <div class="col-md-4" style="font-size: 15px;">
    
                <p class="my-1 py-1" id="model">Model : ${data.model}</p>
                <p class="my-1 py-1" id="battery">Battery Capacity : ${data.batteryLevel}%</p>
               
                <p class="my-1 py-1" id="status">Status : ${data.status}</p>
                 
            </div>
            <!-- Status -->
            <div class="col-md-4 text-center" style="font-size: 15px;">
                <p class="font-weight-bold my-0 py-0">Gadget Image</p>
                
                <img src="/uploads/gadget/${data.serialNumber}/${data.image}" class="img-fluid img-thumbnail"
                     alt="No Image">
            </div>`);
        },
        error: function (error) {

        }

    });


    fillEmployeeSelectionInput();


}

function updateGadgetAssignment() {


    var id = JSON.parse(localStorage.getItem("id"));

    var employeeId = document.getElementById("employeeSelectioninput").value;

    if (employeeId === "") {
        alert("Please Select Employee");
        return;
    }


    $.ajax({
        type: "GET",
        url: "getGadget/" + id,

        success: function (data) {
            console.log(data)
            // IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;

            if (data.status === "Allocated") {
                alert("Sorry You cant proceed , This gadget is already in allocated to someone : Choose another one!");
                return;
            }


            $.ajax({
                type: "PUT",
                url: "/assign/" + id + "/" + employeeId,
                data: data,
                enctype: "multipart/form-data",
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {

                    alert("Gadget Assigned Successfully ");
                    window.location.href = "/";
                }
            });


        },
        error: function (error) {

        }

    });


}

function fillEmployeeSelectionInput() {
    $.ajax({
        type: "GET",
        url: "getEmployees",

        success: function (data) {
            console.log(data);
            var select = $("#employeeSelectioninput");
            select.empty();
            select.append(`<option value="" selected>Select Employee </option>`);
            for (var i = 0; i < data.length; i++) {
                select.append(`<option value="${data[i].employeeId}">${data[i].employeeFirstName}  ${data[i].employeeLastName}</option>`);
            }
        }
    })

}

function loadGadgetsIndex() {

    console.log("Loading Gadgets");

    $.ajax({
        type: "GET",
        url: "getAllGadgets",

        success: function (data) {

            console.log(data);
            //href="/view_gadget" onclick="setLocal('${row.id}')"

            $('#allGadgets').empty();
            // Iterate through the data and populate the HTML template
            data.forEach(function (gadget) {
                var droneHtml = '<div class="col-lg-3 mt-3">' +
                    '<div class="card my-2 my-lg-0">' +
                    '<div class="card-body position-relative p-0">' +
                    `<a href="/viewGadget" onclick="setLocal('` + gadget.serialNumber + `')"><img alt="product image" class="img-fluid" src="uploads/gadget/` + gadget.serialNumber +`/` + gadget.image + `"></a>` +


                    '<a class="btn btn-success btn-sm cart-button" href="#">' + gadget.status + '</a>' +
                    '</div>' +
                    '<div class="card-footer bg-transparent py-0">' +
                    '<div class="row my-1">' +
                    '<div class="col-md-6">' +
                    '<p class="font-weight-bold text-danger"> Battery : ' + gadget.batteryLevel + "%" +
                    ' </p>' +
                    '</div>' +
                    '<div class="col-md-6">' +
                    `<a class="btn btn-success btn-sm btn-block" href="/assignGadget" onclick="setLocal('${gadget.serialNumber}')">Assign</a>` +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';

                // Append the generated HTML to a container element
                $('#allGadgets').append(droneHtml);


            });

            // Add CSS styles for the cart button
            var cartButtonStyle = document.createElement('style');
            cartButtonStyle.innerHTML = '.cart-button { position: absolute; top: 5px; right: 5px; z-index: 1; opacity: 0; transition: opacity 0.3s; }'
                + '.card-body:hover .cart-button { opacity: 1; }';
            document.head.appendChild(cartButtonStyle);

            // Add CSS styles for the like button
            var likeButtonStyle = document.createElement('style');
            likeButtonStyle.innerHTML = '.like-button { position: absolute; bottom: 5px; right: 5px; z-index: 1; opacity: 0; transition: opacity 0.3s; }'
                + '.card-body:hover .like-button { opacity: 1; }';
            document.head.appendChild(likeButtonStyle);


        }
        ,
        error: function (data) {
            console.log("Data= " + data);
        }
    });

}

const gadgetImageInput = document.getElementById('gadgetImage');
const GadgetImagePreview = document.getElementById('gadgetImagePreview');
if (gadgetImageInput !== null) {
    gadgetImageInput.addEventListener('change', function () {
        // Get the selected file
        const file = gadgetImageInput.files[0];

        // Create a FileReader instance
        const reader = new FileReader();

        // Set the image source to the selected file
        reader.onload = function () {
            GadgetImagePreview.src = reader.result;
        }

        // Read the selected file as data URL
        if (file) {
            reader.readAsDataURL(file);
        }
    });

}



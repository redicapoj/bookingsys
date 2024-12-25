// Function to add customer to the database
document.getElementById("customerForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    console.log("Form submitted");

    const formData = new FormData(event.target);
    const responseDiv = document.getElementById("response");

    if (!responseDiv) {
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/bookingsys/add-customer", {
            method: "POST",
            body: new URLSearchParams(formData),
        });

        if (response.ok) {
            const message = await response.text();
            responseDiv.textContent = message;
        } else {
            responseDiv.textContent = "Failed to add customer!";
        }
    } catch (error) {
        console.error("Error:", error);
        responseDiv.textContent = "An error occurred!";
    }
});

// Function to load and display rooms
async function loadRooms() {
    const roomsContainer = document.getElementById("roomsContainer");
    const roomSelect = document.getElementById("room");

    try {
        const response = await fetch("http://localhost:8080/bookingsys/rooms");
        if (!response.ok) {
            roomsContainer.textContent = "Failed to load rooms!";
            return;
        }

        const rooms = await response.json();

        roomsContainer.innerHTML = '';
        roomSelect.innerHTML = '';

        const defaultOption = document.createElement("option");
        defaultOption.textContent = "Select a room";
        defaultOption.value = "";
        roomSelect.appendChild(defaultOption);

        rooms.forEach(room => {
            const roomOption = document.createElement("option");
            roomOption.value = room.id;
            roomOption.textContent = `${room.name} (${room.type})`;
            roomSelect.appendChild(roomOption);

            const roomElement = document.createElement("div");
            roomElement.classList.add("room");
            roomElement.innerHTML = `
                <h3>${room.name}</h3>
                <p>Type: ${room.type}</p>
                <p>Description: ${room.description}</p>
                <p>Price per night: $${room.pricePerNight}</p>
                <p>Status: ${room.status}</p>
            `;
            roomsContainer.appendChild(roomElement);
        });
    } catch (error) {
        console.error("Error loading rooms:", error);
        roomsContainer.textContent = "An error occurred while loading rooms.";
    }
}

// Call the function to load rooms when the page loads
window.onload = loadRooms;

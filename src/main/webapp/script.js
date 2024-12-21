document.getElementById("customerForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    console.log("Form submitted");

    const formData = new FormData(event.target);
    const responseDiv = document.getElementById("response");

    if (!responseDiv) {
        console.error("Response div not found!");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/bookingsys-1/add-customer", {
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

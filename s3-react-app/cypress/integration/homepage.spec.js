describe("home page tests", () => {
    it("renders corretly", () => {
        cy.visit("/")
        cy.get(".container").should("exist");
    })

    it("make a reservation", () => {
        cy.visit("/")

        /* ==== Generated with Cypress Studio ==== */
        cy.get('.banner-btn').click();
        cy.get('[type="text"]').clear();
        cy.get('[type="text"]').type('darioooo');
        cy.get('[type="password"]').clear();
        cy.get('[type="password"]').type('password123');
        cy.get('button').click();
        cy.get('.nav-logo').click();

        cy.get('#check-in').click();
        cy.get('#check-out').click();
        cy.get('#guests').click();
        cy.get('#room').select('1');
        cy.get('button').click();
        /* ==== End Cypress Studio ==== */
    })

    it("featured room should exist", () => {
        cy.visit('/')

        cy.findByText('Double Room').should("exist")
    })
})
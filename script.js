// File: src/main/webapp/js/script.js

// Utility functions for the department store system
document.addEventListener('DOMContentLoaded', function() {
    initializeDatePickers();
    initializeSearchEnhancements();
    initializeReportFilters();
});

// Initialize date pickers with default values
function initializeDatePickers() {
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    
    if (startDateInput && endDateInput) {
        // Set default start date to 30 days ago
        const thirtyDaysAgo = new Date();
        thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 30);
        
        // Set default end date to today
        const today = new Date();
        
        startDateInput.value = formatDate(thirtyDaysAgo);
        endDateInput.value = formatDate(today);
        
        // Add validation to ensure start date <= end date
        startDateInput.addEventListener('change', validateDateRange);
        endDateInput.addEventListener('change', validateDateRange);
    }
}

// Format date as YYYY-MM-DD for input[type=date]
function formatDate(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

// Validate that start date is not after end date
function validateDateRange() {
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    
    if (startDateInput && endDateInput) {
        const startDate = new Date(startDateInput.value);
        const endDate = new Date(endDateInput.value);
        
        if (startDate > endDate) {
            alert('Start date cannot be after end date');
            startDateInput.value = endDateInput.value;
        }
    }
}

// Enhance search functionality
function initializeSearchEnhancements() {
    const searchInput = document.querySelector('input[name="keyword"]');
    const searchForm = document.querySelector('.search-form');
    
    if (searchInput && searchForm) {
        // Add real-time search suggestions (could be enhanced with AJAX)
        searchInput.addEventListener('input', function() {
            if (this.value.length > 2) {
                // In a real application, you would make an AJAX call here
                console.log('Searching for:', this.value);
            }
        });
        
        // Prevent empty search
        searchForm.addEventListener('submit', function(e) {
            if (!searchInput.value.trim()) {
                e.preventDefault();
                alert('Please enter a search term');
                searchInput.focus();
            }
        });
    }
}

// Initialize report filters and quick actions
function initializeReportFilters() {
    // Quick date range buttons
    const quickButtons = document.querySelectorAll('.btn.small');
    quickButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            if (this.textContent.includes('Q1')) {
                setDateRange('2024-01-01', '2024-03-31');
            } else if (this.textContent.includes('March')) {
                setDateRange('2024-03-01', '2024-03-31');
            } else if (this.textContent.includes('February')) {
                setDateRange('2024-02-01', '2024-02-29');
            }
        });
    });
}

function setDateRange(startDate, endDate) {
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    
    if (startDateInput && endDateInput) {
        startDateInput.value = startDate;
        endDateInput.value = endDate;
    }
}

// Utility function for formatting currency
function formatCurrency(amount) {
    return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD'
    }).format(amount);
}

// Add loading indicators for better UX
function showLoading(element) {
    element.disabled = true;
    element.innerHTML = '<span class="loading">Loading...</span>';
}

function hideLoading(element, originalText) {
    element.disabled = false;
    element.textContent = originalText;
}

// Export functions for use in other scripts
window.DepartmentStore = {
    formatCurrency,
    showLoading,
    hideLoading
};
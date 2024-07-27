document.addEventListener("DOMContentLoaded", function () {
    var tooltips = document.querySelectorAll('.tooltip');

    tooltips.forEach(function (tooltip) {
        var tooltipText = tooltip.querySelector('.tooltiptext');

        tooltip.addEventListener('mouseover', function () {
            tooltipText.style.visibility = 'visible';
            tooltipText.style.opacity = '1';
        });

        tooltip.addEventListener('mouseout', function () {
            tooltipText.style.visibility = 'hidden';
            tooltipText.style.opacity = '0';
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var modalLinks = document.querySelectorAll('.diploma-link');
    var modals = document.querySelectorAll('.diploma-modal');
    var closeButtons = document.querySelectorAll('.close');

    modalLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            var targetModalId = this.getAttribute('href').substring(1);
            var targetModal = document.getElementById(targetModalId);
            if (targetModal) {
                targetModal.style.display = 'block';
            }
        });
    });

    closeButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            this.parentElement.style.display = 'none';
        });
    });

    window.addEventListener('click', function (event) {
        modals.forEach(function (modal) {
            if (event.target == modal) {
                modal.style.display = 'none';
            }
        });
    });
});

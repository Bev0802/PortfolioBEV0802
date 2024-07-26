document.addEventListener("DOMContentLoaded", function() {
    var tooltips = document.querySelectorAll('.tooltip');

    tooltips.forEach(function(tooltip) {
        var tooltipText = tooltip.querySelector('.tooltiptext');

        tooltip.addEventListener('mouseover', function() {
            tooltipText.style.visibility = 'visible';
            tooltipText.style.opacity = '1';
        });

        tooltip.addEventListener('mouseout', function() {
            tooltipText.style.visibility = 'hidden';
            tooltipText.style.opacity = '0';
        });
    });
});

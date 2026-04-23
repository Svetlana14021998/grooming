(function() {
    function scrollToFeedbackForm(isError) {
        const feedbackForm = document.getElementById('feedbackForm');
        if (!feedbackForm) return;

        let indicator = null;
        if (!isError && window.messages && window.messages.scrollingToForm) {
            indicator = document.querySelector('.scroll-indicator');
            if (!indicator) {
                indicator = document.createElement('div');
                indicator.className = 'scroll-indicator';
                indicator.innerHTML = '<span>👇</span> <span>' + window.messages.scrollingToForm + '</span>';
                document.body.appendChild(indicator);
            }
        }

        document.documentElement.style.scrollBehavior = 'auto';
        window.scrollTo(0, 0);

        function smoothScrollTo(element, duration) {
            const targetPosition = element.getBoundingClientRect().top + window.pageYOffset - 50;
            const startPosition = window.pageYOffset;
            const distance = targetPosition - startPosition;
            let startTime = null;

            function animation(currentTime) {
                if (startTime === null) startTime = currentTime;
                const elapsed = currentTime - startTime;
                const progress = Math.min(elapsed / duration, 1);
                const easeProgress = 1 - Math.pow(1 - progress, 3);
                window.scrollTo(0, startPosition + (distance * easeProgress));

                if (progress < 1) {
                    requestAnimationFrame(animation);
                } else {
                    const highlightClass = isError ? 'highlight-error' : 'highlight';
                    feedbackForm.classList.add(highlightClass);
                    setTimeout(() => feedbackForm.classList.remove(highlightClass), 2000);
                    if (indicator) setTimeout(() => indicator.remove(), 2500);
                    setTimeout(() => {
                        document.documentElement.style.scrollBehavior = '';
                    }, 500);
                }
            }
            requestAnimationFrame(animation);
        }

        setTimeout(() => smoothScrollTo(feedbackForm, 1500), 100);
    }

    if (window.location.hash === '#feedbackForm') {
        history.replaceState(null, null, window.location.pathname + window.location.search);
        scrollToFeedbackForm(false);
    } else {
        const hasValidationErrors = document.querySelector('.is-invalid, .error-message');
        if (hasValidationErrors) scrollToFeedbackForm(true);
    }
})();
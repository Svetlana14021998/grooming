/**
 * Генерация цветов для типов статей
 * Используется на всех страницах с бейджами типов статей
 */

(function() {
    'use strict';

    /**
     * Генерация цвета из строки
     * @param {string} str - Тип статьи
     * @returns {string} CSS-цвет в формате hsl
     */
    function getColorFromString(str) {
        if (!str || str === '' || str === 'general' || str === 'unknow') {
            return '#6c7a6e';
        }

        // 1. Учитываем длину строки
        const length = str.length;

        // 2. Сумма кодов символов с учетом позиции
        let hash = 0;
        for (let i = 0; i < length; i++) {
            const code = str.charCodeAt(i);
            hash = (hash * 31 + code * (i + 1)) % 1000000;
        }

        // 3. Добавляем количество слов
        const words = str.split(/\s+/).filter(w => w.length > 0);
        hash = (hash + words.length * 12345) % 1000000;

        // 4. Добавляем длину строки
        hash = (hash + length * 6789) % 1000000;

        // 5. Преобразуем хеш в цвет
        const hue = Math.abs(hash) % 360;
        const saturation = 65 + (Math.abs(hash) % 30);
        const lightness = 45 + (Math.abs(hash) % 25);

        return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
    }

    /**
     * Применение цветов к бейджам типов статей
     */
    function applyColorsToBadges() {
        const badges = document.querySelectorAll('.article-type-badge, .article-type-badge-large');

        badges.forEach(function(badge) {
            const typeText = badge.textContent.trim();
            if (typeText && typeText !== 'unknow' && typeText !== 'general' && typeText !== '') {
                const color = getColorFromString(typeText);
                badge.style.background = color;
                badge.style.boxShadow = `0 2px 12px ${color}66`;
                badge.style.color = '#ffffff';
            } else {
                badge.style.background = '#6c7a6e';
                badge.style.boxShadow = '0 2px 8px rgba(0, 0, 0, 0.08)';
                badge.style.color = '#ffffff';
            }
        });
    }

    // Запускаем после загрузки DOM
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', applyColorsToBadges);
    } else {
        // DOM уже загружен
        applyColorsToBadges();
    }

    // Экспортируем функцию для возможного использования в других скриптах
    window.articleTypeColors = {
        getColorFromString: getColorFromString,
        applyColorsToBadges: applyColorsToBadges
    };

})();
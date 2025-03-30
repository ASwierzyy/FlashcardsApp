import {
    fetchEntries,
    addEntry,
    deleteEntry,
    searchEntries
} from './api.js';

import {
    renderEntryList,
    renderSearchResults,
    clearInputs,
    toggleEntries
} from './ui.js';

import { startQuiz } from './quiz.js';

document.addEventListener('DOMContentLoaded', () => {

    document.getElementById('toggleEntriesBtn').addEventListener('click', () => {
        toggleEntries();
    });

    document.getElementById('sortBy').addEventListener('change', loadEntries);
    document.getElementById('order').addEventListener('change', loadEntries);

    document.getElementById('addBtn').addEventListener('click', async () => {
        const polish = document.getElementById('polish').value;
        const english = document.getElementById('english').value;
        const german = document.getElementById('german').value;

        if (!polish || !english || !german) {
            alert('Please fill all fields!');
            return;
        }

        try {
            await addEntry(polish, english, german);
            clearInputs('polish', 'english', 'german');
            loadEntries();
        } catch (err) {
            alert('Error adding entry: ' + err.message);
        }
    });

    document.getElementById('searchBtn').addEventListener('click', async () => {
        const type = document.getElementById('searchType').value;
        const value = document.getElementById('searchValue').value;
        try {
            const results = await searchEntries(type, value);
            renderSearchResults(results, 'searchResults');
        } catch (err) {
            alert('Error searching: ' + err.message);
        }
    });

    document.getElementById('quizBtn').addEventListener('click', startQuiz);

    loadEntries();
});

async function loadEntries() {
    const sortBy = document.getElementById('sortBy').value;
    const order = document.getElementById('order').value;

    try {
        const entries = await fetchEntries(sortBy, order);
        renderEntryList(entries, 'entries', async (id) => {
            try {
                await deleteEntry(id);
                loadEntries();
            } catch (err) {
                alert('Error deleting entry: ' + err.message);
            }
        });
    } catch (err) {
        alert('Error loading entries: ' + err.message);
    }
}
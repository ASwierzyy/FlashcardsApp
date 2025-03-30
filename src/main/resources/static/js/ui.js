export function renderEntryList(entries, containerId, onDelete) {
    const container = document.getElementById(containerId);
    container.innerHTML = '';

    entries.forEach(entry => {
        const div = document.createElement('div');
        div.innerHTML = `
      <span>[${entry.id}] PL: ${entry.polish} | EN: ${entry.english} | DE: ${entry.german}</span>
      <button>Delete</button>
    `;
        container.appendChild(div);

        const deleteBtn = div.querySelector('button');
        deleteBtn.addEventListener('click', () => onDelete(entry.id));
    });
}

export function renderSearchResults(entries, containerId) {
    renderEntryList(entries, containerId, () => {});
}

export function clearInputs(...ids) {
    ids.forEach(id => {
        const input = document.getElementById(id);
        if (input) input.value = '';
    });
}

export function toggleEntries() {
    const entriesDiv = document.getElementById('entries');
    const toggleBtn = document.getElementById('toggleEntriesBtn');
    const isHidden = entriesDiv.style.display === 'none';

    if (isHidden) {
        entriesDiv.style.display = 'block';
        toggleBtn.textContent = 'Hide Entries';
    } else {
        entriesDiv.style.display = 'none';
        toggleBtn.textContent = 'Show Entries';
    }
}

export function renderQuizQuestion(question, quizAreaId) {
    const quizDiv = document.getElementById(quizAreaId);
    quizDiv.innerHTML = '';

    if (!question) {
        quizDiv.innerHTML = '<p>No entries found for quiz.</p>';
        return;
    }

    const { entryId, sourceLang, sourceWord, targetLangs } = question;

    quizDiv.innerHTML = `
    <p>Translate this <b>${sourceLang.toUpperCase()}</b> word: <em>${sourceWord}</em></p>
    <label>${targetLangs[0]}:</label>
    <input id="answerLang1" />
    <label>${targetLangs[1]}:</label>
    <input id="answerLang2" />
    <button id="checkQuizBtn">Check</button>

    <input type="hidden" id="quizEntryId" value="${entryId}" />
    <input type="hidden" id="quizSourceLang" value="${sourceLang}" />
  `;
}

export function showQuizResult(result) {
    if (result.correct) {
        alert('Correct!');
    } else {
        alert(`Incorrect!\nCorrect answers: ${result.correctLang1}, ${result.correctLang2}`);
    }
}
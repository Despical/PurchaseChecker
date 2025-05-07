const folderList = document.getElementById('folder-list');
const tocList = document.getElementById('toc-list');
const content = document.querySelector('.content');
const folderData = fetch('/api/v1/docs')

folderData
    .then(response => response.json())
    .then((data) => {
        for (const folder in data) {
            if (data.hasOwnProperty(folder)) {
                const folderItem = document.createElement('li');

                const accordion = document.createElement('button');
                accordion.classList.add('accordion');
                accordion.textContent = folder;
                folderItem.appendChild(accordion);

                const panel = document.createElement('div');
                panel.classList.add('panel');

                data[folder].forEach(subDoc => {
                    const subDocItem = document.createElement('a');
                    subDocItem.href = `http://localhost:8080/docs/${
                        folder.toLowerCase().replace(/ /g, '-')
                    }/${subDoc.toLowerCase().replace(/ /g, '-')}`;

                    subDocItem.textContent = subDoc;
                    subDocItem.addEventListener('click', () => fetchContent(subDoc));

                    panel.appendChild(subDocItem);
                });

                folderItem.appendChild(panel);
                folderList.appendChild(folderItem);

                accordion.addEventListener('click', () => {
                    panel.style.display = panel.style.display === 'block' ? 'none' : 'block';
                    accordion.classList.toggle('active');
                });
            }
        }
    })
    .catch(error => console.error('Error fetching data:', error));


function fetchContent(subDoc) {
    fetch(`/docs/${subDoc}`)
        .then(response => response.text())
        .then(data => content.innerHTML = data)
        .catch(error => console.error('Error while loading content:', error));
}

content.querySelectorAll('h1, h2, h3').forEach((h, index) => {
    const id = 'heading-' + index;
    h.id = id;

    const li = document.createElement('li');
    const a = document.createElement('a');
    a.href = '#' + id;
    a.textContent = h.textContent;

    li.appendChild(a);
    tocList.appendChild(li);
});

document.addEventListener('DOMContentLoaded', function () {
    const links = document.querySelectorAll('.sidebar a');
    const currentUrl = window.location.pathname;

    links.forEach(link => {
        if (link.getAttribute('href') === currentUrl) {
            link.classList.add('selected');
        }
    });
});

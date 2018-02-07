(() => {
    $(document).ready(() => {
        let imgs = $("#imgs");
        $("#iptImgs").change(e => {
            imgs.children().remove();
            for (let file of e.currentTarget.files) {
                if (file.type.toLowerCase().match("image/.*")) {
                    let fileReader = new FileReader();
                    fileReader.onloadend = () => {
                        if (fileReader.readyState === fileReader.DONE) {
                            imgs.append(`<img src="${fileReader.result}">`);
                        }
                    };
                    fileReader.readAsDataURL(file);
                }
            }
        });

        $("#btnExPdf").click(e => {
             if (imgs.find("img").length > 0) {
                 let pdf = new jspdf('p', 'pt', 'a4');
                 pdf.internal.scaleFactor = 1;
                 let options = {
                     pagesplit: false
                 };
                 pdf.addHTML(imgs, options, function() {
                     pdf.save('test.pdf');
                 });
             }
             else alert("还未选择图片");
        });
    });
})();


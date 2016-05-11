(function (c) {
    if (window.KindEditor === c) {
        var b = {
            version: "3.5.5 (2016-04-22)"
        };
        b.scriptPath = function () {
            for (var a = document.getElementsByTagName("script"),
                     d = 0,
                     b = a.length; d < b;
                 d++) {
                var k = a[d].src;
                if (k && k.match(/kindeditor[\w\-\.]*\.js/))
                    return k.substring(0, k.lastIndexOf("/") + 1);
            }
            return "";
        }();
        b.browser = function () {
            var a = navigator.userAgent.toLowerCase();
            return {
                VERSION: a.match(/(msie|firefox|webkit|opera)[\/:\s](\d+)/)
                    ? RegExp.$2 : "0",
                IE: a.indexOf("msie") > -1 && a.indexOf("opera") == -1,
                GECKO: a.indexOf("gecko") > -1 && a.indexOf("khtml") == -1,
                WEBKIT: a.indexOf("applewebkit") > -1,
                OPERA: a.indexOf("opera") > -1
            };
        }();
        b.setting = {
            wyswygMode: !0,
            loadStyleMode: !0,
            resizeMode: 2,
            filterMode: !1,
            autoSetDataMode: !1,
            shadowMode: !0,
            useContextmenu: !0,
            urlType: "",
            skinType: "default",
            syncType: "form",
            newlineTag: "p",
            dialogAlignType: "page",
            cssPath: "",
            skinsPath: b.scriptPath + "skins/",
            pluginsPath: b.scriptPath + "plugins/",
            minWidth: 200,
            minHeight: 100,
            minChangeSize: 5,
            toolbarLineHeight: 24,
            statusbarHeight: 11,
            items: ["source", "|", "fullscreen", "undo", "redo",
                "print", "cut", "copy", "paste", "plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "|", "selectall", "-", "title", "fontname", "fontsize", "|", "textcolor", "bgcolor", "bold", "italic", "underline", "strikethrough", "removeformat", "|", "image", "flash", "media", "advtable", "hr", "emoticons", "link", "unlink", "|", "about"],
            colorTable: [["#E53333", "#E56600", "#FF9900", "#64451D", "#DFC5A4", "#FFE500"], ["#009900",
                "#006600", "#99BB00", "#B8D100", "#60D978", "#00D5FF"],
                ["#337FE5", "#003399", "#4C33E5", "#9933E5", "#CC33E5", "#EE33EE"],
                ["#FFFFFF", "#CCCCCC", "#999999", "#666666", "#333333", "#000000"]
            ],
            noEndTags: ["br", "hr", "img", "area", "col", "embed", "input", "param"],
            inlineTags: ["b", "del", "em", "font", "i", "span", "strike", "strong", "sub", "sup", "u"],
            endlineTags: ["br", "hr", "table", "tbody", "td", "tr", "th", "div", "p", "ol", "ul", "li", "blockquote", "h1", "h2", "h3", "h4", "h5", "h6", "script",
                "style", "marquee"],
            htmlTags: {
                font: ["color", "size",
                    "face", ".background-color"],
                span: [".color", ".background-color", ".font-size", ".font-family", ".background", ".font-weight", ".font-style", ".text-decoration", ".vertical-align"],
                div: ["align", ".border", ".margin", ".padding", ".text-align", ".color",
                    ".background-color",
                    ".font-size", ".font-family", ".font-weight", ".background",
                    ".font-style", ".text-decoration", ".vertical-align", ".margin-left"],
                table: ["border", "cellspacing", "cellpadding", "width", "height", "align",
                    "bordercolor", ".padding", ".margin", ".border", "bgcolor",
                    ".text-align", ".color", ".background-color", ".font-size",
                    ".font-family", ".font-weight", ".font-style", ".text-decoration", ".background",
                    ".width", ".height"],
                "td,th": ["align", "valign", "width", "height", "colspan", "rowspan", "bgcolor", ".text-align",
                    ".color", ".background-color", ".font-size", ".font-family",
                    ".font-weight", ".font-style",
                    ".text-decoration", ".vertical-align",
                    ".background"],
                a: ["href", "target", "name"],
                embed: ["src", "width", "height", "type", "loop", "autostart", "quality", ".width", ".height", "align", "allowscriptaccess",
                    "/"],
                img: ["src", "width", "height", "border", "alt", "title", ".width", ".height", "/"],
                hr: ["/"],
                br: ["/"],
                "p,ol,ul,li,blockquote,h1,h2,h3,h4,h5,h6": ["align", ".text-align", ".color", ".background-color", ".font-size", ".font-family", ".background", ".font-weight", ".font-style", ".text-decoration", ".vertical-align", ".text-indent", ".margin-left"],
                "tbody,tr,strong,b,sub,sup,em,i,u,strike": []
            },
            mediaTypes: {
                rm: "audio/x-pn-realaudio-plugin",
                flash: "application/x-shockwave-flash",
                media: "video/x-ms-asf-plugin"
            },
            afterTab: function (a) {
                b.util.setSelection(a);
                b.util.insertHtml(a, "&nbsp;&nbsp;&nbsp;&nbsp;");
            }
        };
        b.g = {};
        b.plugin = {};
        b.$ = function (a, d) {
            return (d || document).getElementById(a);
        };
        b.$$ = function (a, d) {
            return (d || document).createElement(a);
        };
        b.event = {
            add: function (a, d, f, k) {
                a.addEventListener ? a.addEventListener(d, f, !1) :
                a.attachEvent && a.attachEvent("on" + d, f);
                k !== c && b.g[k].eventStack.push({el: a, type: d, fn: f});
            },
            remove: function (a, d, f, k) {
                a.removeEventListener ? a.removeEventListener(d, f, !1) :
                a.detachEvent && a.detachEvent("on" + d, f);
                if (k !== c)for (var k = b.g[k].eventStack,
                                     h = 0, e = k.length;
                                 h < e; h++) {
                    var i = k[h];
                    i && a === i.el && d === i.type && f === i.fn && delete k[h];
                }
            },
            stopPropagation: function (a) {
                a.stopPropagation && a.stopPropagation();
                if (a.cancelBubble !== c)a.cancelBubble = !0
            }, preventDefault: function (a) {
                a.preventDefault && a.preventDefault();
                if (a.returnValue !== c)a.returnValue = !1
            }, stop: function (a) {
                this.stopPropagation(a);
                this.preventDefault(a)
            }, bind: function (a, d, f, k) {
                this.add(a, d, function (a) {
                    f(a);
                    b.event.stop(a);
                    return !1
                }, k)
            }, input: function (a, d, f) {
                function k(a) {
                    window.setTimeout(function () {
                            d(a)
                        },
                        1)
                }

                this.add(a, "keyup", function (a) {
                    if (!a.ctrlKey && !a.altKey && (a.keyCode < 16 || a.keyCode > 18) && a.keyCode != 116)return d(a), b.event.stop(a), !1
                }, f);
                a = a.nodeName == "#document" ? a.body : a;
                this.add(a, "paste", k, f);
                this.add(a, "cut", k, f)
            }, ctrl: function (a, d, f, k) {
                d = d.toString().match(/^\d{2,}$/) ? d : d.toUpperCase().charCodeAt(0);
                this.add(a, "keydown", function (a) {
                    if (a.ctrlKey && a.keyCode == d && !a.shiftKey && !a.altKey)return f(a), b.event.stop(a), !1
                }, k)
            }, ready: function (a, d, b, k) {
                var d = d || window, b = b || document, c = !1, e = function () {
                    c ||
                    (c = !0, a())
                };
                if (b.addEventListener)this.add(b, "DOMContentLoaded", e, k); else if (b.attachEvent && (this.add(b, "readystatechange", function () {
                        b.readyState == "complete" && e()
                    }, k), b.documentElement.doScroll && typeof d.frameElement === "undefined")) {
                    var i = function () {
                        if (!c) {
                            try {
                                b.documentElement.doScroll("left")
                            } catch (a) {
                                window.setTimeout(i, 0);
                                return
                            }
                            e()
                        }
                    };
                    i()
                }
                this.add(d, "load", e, k)
            }
        };
        b.each = function (a, d) {
            for (var b in a)a.hasOwnProperty(b) && d(b, a[b])
        };
        b.eachNode = function (a, d) {
            var f = function (a) {
                if (b.util.getNodeType(a) !=
                    1)return !0;
                for (a = a.firstChild; a;) {
                    var c = a.nextSibling;
                    if (!d(a))return !1;
                    if (!f(a))return !1;
                    a = c
                }
                return !0
            };
            f(a)
        };
        b.selection = function (a) {
            this.keRange = this.range = this.sel = null;
            this.isControl = !1;
            var d = a.parentWindow || a.defaultView;
            this.init = function () {
                var f = a.selection ? a.selection : d.getSelection(), c;
                try {
                    c = f.rangeCount > 0 ? f.getRangeAt(0) : f.createRange()
                } catch (h) {
                }
                c || (c = b.util.createRange(a));
                this.sel = f;
                this.range = c;
                var e, i, l;
                if (b.browser.IE)c.item ? (this.isControl = !0, f = i = c.item(0), e = l = 0) : (this.isControl = !1, f = function (d) {
                    var f = c.duplicate();
                    f.collapse(d);
                    var h = f.parentElement(), e = h.childNodes;
                    if (e.length == 0)return {node: h, pos: 0};
                    var g, i = 0, l = !1, j = c.duplicate();
                    b.util.moveToElementText(j, h);
                    for (var p = 0, v = e.length; p < v; p++) {
                        var d = e[p], t = j.compareEndPoints("StartToStart", f);
                        if (t > 0)l = !0; else if (t == 0)return d.nodeType == 1 ? (f = new b.range(a), f.selectTextNode(d), {
                            node: f.startNode,
                            pos: 0
                        }) : {node: d, pos: 0};
                        d.nodeType == 1 ? (t = c.duplicate(), b.util.moveToElementText(t, d), j.setEndPoint("StartToEnd", t), l ? i += t.text.replace(/\r\n|\n|\r/g,
                            "").length : i = 0) : d.nodeType == 3 && typeof d.nodeValue === "string" && (j.moveStart("character", d.nodeValue.length), i += d.nodeValue.length);
                        l || (g = d)
                    }
                    if (!l && g.nodeType == 1)return g = h.lastChild, {
                        node: g,
                        pos: g.nodeType == 1 ? 1 : g.nodeValue.length
                    };
                    j = c.duplicate();
                    b.util.moveToElementText(j, h);
                    j.setEndPoint("StartToEnd", f);
                    i -= j.text.replace(/\r\n|\n|\r/g, "").length;
                    return {node: g, pos: i}
                }, e = f(!0), l = f(!1), f = e.node, e = e.pos, i = l.node, l = l.pos); else {
                    f = c.startContainer;
                    e = c.startOffset;
                    i = c.endContainer;
                    l = c.endOffset;
                    f.nodeType ==
                    1 && typeof f.childNodes[e] != "undefined" && (f = f.childNodes[e], e = 0);
                    i.nodeType == 1 && (l = l == 0 ? 1 : l, typeof i.childNodes[l - 1] != "undefined" && (i = i.childNodes[l - 1], l = i.nodeType == 1 ? 0 : i.nodeValue.length));
                    this.isControl = f.nodeType == 1 && f === i && c.startOffset + 1 == c.endOffset;
                    if (f.nodeType == 1 && i.nodeType == 3 && l == 0 && i.previousSibling)for (var j = i.previousSibling; j;) {
                        if (j === f) {
                            i = f;
                            break
                        }
                        if (j.childNodes.length != 1)break;
                        j = j.childNodes[0]
                    }
                    if (c.collapsed)j = new b.range(a), j.setTextStart(f, e), i = j.startNode, l = j.startPos
                }
                j = new b.range(a);
                j.setTextStart(f, e);
                j.setTextEnd(i, l);
                this.keRange = j
            };
            this.init();
            this.addRange = function (d) {
                if (!(b.browser.GECKO && b.browser.VERSION < 3))if (this.keRange = d, b.browser.IE) {
                    var c = function (c) {
                        var k = b.util.createRange(a), h = c ? d.startNode : d.endNode;
                        h.nodeType == 1 ? (b.util.moveToElementText(k, h), k.collapse(c)) : h.nodeType == 3 && (k = b.util.getNodeStartRange(a, h), k.moveStart("character", c ? d.startPos : d.endPos));
                        return k
                    };
                    if (!this.range.item) {
                        var h = d.startNode;
                        h == d.endNode && b.util.getNodeType(h) == 1 && b.util.getNodeTextLength(h) ==
                        0 ? (c = a.createTextNode(" "), h.appendChild(c), b.util.moveToElementText(this.range, h), this.range.collapse(!1), this.range.select(), h.removeChild(c)) : (h.nodeType == 3 && d.collapsed() ? (this.range = c(!0), this.range.collapse(!0)) : (this.range.setEndPoint("StartToStart", c(!0)), this.range.setEndPoint("EndToStart", c(!1))), this.range.select())
                    }
                } else {
                    h = function (a) {
                        for (var d = 0; a;)a = a.previousSibling, d++;
                        return --d
                    };
                    c = new b.range(a);
                    c.setTextStart(d.startNode, d.startPos);
                    c.setTextEnd(d.endNode, d.endPos);
                    var e = c.startNode,
                        i = c.endNode;
                    b.util.getNodeType(e) == 88 ? this.range.setStart(e.parentNode, h(c.startNode)) : this.range.setStart(e, c.startPos);
                    b.util.getNodeType(i) == 88 ? this.range.setEnd(i.parentNode, h(c.endNode) + 1) : this.range.setEnd(i, c.endPos);
                    this.sel.removeAllRanges();
                    this.sel.addRange(this.range)
                }
            };
            this.focus = function () {
                b.browser.IE && this.range != null && this.range.select()
            }
        };
        b.range = function (a) {
            this.endPos = this.endNode = this.startPos = this.startNode = null;
            this.getParentElement = function () {
                var d = function (a, d) {
                    for (; a && (!a.tagName ||
                    a.tagName.toLowerCase() != "body");)if (a = a.parentNode, d(a))break
                }, f = [];
                d(this.startNode, function (a) {
                    f.push(a)
                });
                var c;
                d(this.endNode, function (a) {
                    if (b.util.inArray(a, f))return c = a, !0
                });
                return c ? c : a.body
            };
            this.getNodeList = function () {
                var d = this, f = this.getParentElement(), c = [], h = !1;
                f == d.startNode && (h = !0);
                h && c.push(f);
                b.eachNode(f, function (f) {
                    f == d.startNode && (h = !0);
                    var e = new b.range(a);
                    e.selectTextNode(f);
                    var l = e.comparePoints("START_TO_END", d);
                    if (l > 0)return !1; else if (l == 0 && (e.startNode !== e.endNode || e.startPos !==
                        e.endPos))return !1;
                    h && c.push(f);
                    return !0
                });
                return c
            };
            this.comparePoints = function (d, f) {
                var c = function (f, c, k, e) {
                    if (b.browser.IE) {
                        var j = function (d, f, c) {
                            var k = b.util.createRange(a), e = b.util.getNodeType(d);
                            e == 1 ? (b.util.moveToElementText(k, d), k.collapse(c)) : e == 3 && (k = b.util.getNodeStartRange(a, d), k.moveStart("character", f), k.collapse(!0));
                            return k
                        }, m;
                        m = d == "START_TO_START" || d == "START_TO_END" ? j(f, c, !0) : j(f, c, !1);
                        f = d == "START_TO_START" || d == "END_TO_START" ? j(k, e, !0) : j(k, e, !1);
                        return m.compareEndPoints("StartToStart",
                            f)
                    } else m = b.util.createRange(a), m.selectNode(f), d == "START_TO_START" || d == "START_TO_END" ? m.collapse(!0) : m.collapse(!1), f = b.util.createRange(a), f.selectNode(k), d == "START_TO_START" || d == "END_TO_START" ? f.collapse(!0) : f.collapse(!1), c = m.compareBoundaryPoints(Range.START_TO_START, f) > 0 ? 1 : m.compareBoundaryPoints(Range.START_TO_START, f) == 0 ? c > e ? 1 : c == e ? 0 : -1 : -1;
                    return c
                };
                if (d == "START_TO_START")return c(this.startNode, this.startPos, f.startNode, f.startPos);
                if (d == "START_TO_END")return c(this.startNode, this.startPos,
                    f.endNode, f.endPos);
                if (d == "END_TO_START")return c(this.endNode, this.endPos, f.startNode, f.startPos);
                if (d == "END_TO_END")return c(this.endNode, this.endPos, f.endNode, f.endPos)
            };
            this.collapsed = function () {
                return this.startNode === this.endNode && this.startPos === this.endPos
            };
            this.collapse = function (a) {
                a ? this.setEnd(this.startNode, this.startPos) : this.setStart(this.endNode, this.endPos)
            };
            this.setTextStart = function (a, f) {
                var c = a;
                b.eachNode(a, function (a) {
                    if (b.util.getNodeType(a) == 3 && a.nodeValue.length > 0 || b.util.getNodeType(a) ==
                        88)return c = a, f = 0, !1;
                    return !0
                });
                this.setStart(c, f)
            };
            this.setStart = function (a, b) {
                this.startNode = a;
                this.startPos = b;
                if (this.endNode === null)this.endNode = a, this.endPos = b
            };
            this.setTextEnd = function (a, f) {
                var c = a;
                b.eachNode(a, function (a) {
                    if (b.util.getNodeType(a) == 3 && a.nodeValue.length > 0 || b.util.getNodeType(a) == 88)c = a, f = b.util.getNodeType(a) == 3 ? a.nodeValue.length : 0;
                    return !0
                });
                this.setEnd(c, f)
            };
            this.setEnd = function (a, b) {
                this.endNode = a;
                this.endPos = b;
                if (this.startNode === null)this.startNode = a, this.startPos = b
            };
            this.selectNode = function (a) {
                this.setStart(a, 0);
                this.setEnd(a, a.nodeType == 1 ? 0 : a.nodeValue.length)
            };
            this.selectTextNode = function (a) {
                this.setTextStart(a, 0);
                this.setTextEnd(a, a.nodeType == 1 ? 0 : a.nodeValue.length)
            };
            this.extractContents = function (d) {
                function f(a, b, f) {
                    var c = a.nodeValue.length, k = a.cloneNode(!0).splitText(b);
                    k.splitText(f - b);
                    if (d) {
                        var e = a;
                        b > 0 && (e = a.splitText(b));
                        f < c && e.splitText(f - b);
                        m.push(e)
                    }
                    return k
                }

                function k(m, q) {
                    if (b.util.getNodeType(m) != 1)return !0;
                    for (var p = m.firstChild; p;) {
                        p == g && (n = !0);
                        p == l && (r = !0);
                        var s = p.nextSibling, t = p.nodeType;
                        if (t == 1)if (t = new b.range(a), t.selectNode(p), t = t.comparePoints("END_TO_END", e), n && (t < 0 || t == 0 && o[p.nodeName.toLowerCase()] !== c))t = p.cloneNode(!0), q.appendChild(t), d && p.parentNode.removeChild(p); else {
                            if (t = p.cloneNode(!1), o[t.nodeName.toLowerCase()] === c && (q.appendChild(t), !k(p, t)))return !1
                        } else if (t == 3 && n)if (p == g && p == l)return p = f(p, i, j), q.appendChild(p), !1; else if (p == g)p = f(p, i, p.nodeValue.length), q.appendChild(p); else if (p == l)return p = f(p, 0, j), q.appendChild(p),
                            !1; else p = f(p, 0, p.nodeValue.length), q.appendChild(p);
                        p = s;
                        if (r)return !1
                    }
                    q.innerHTML.replace(/<.*?>/g, "") === "" && q.parentNode && q.parentNode.removeChild(q);
                    return !0
                }

                var d = d === c ? !0 : d, e = this, g = this.startNode, i = this.startPos, l = this.endNode, j = this.endPos, m = [], o = b.util.arrayToHash(b.setting.noEndTags), n = !1, r = !1, s = this.getParentElement(), q = s.cloneNode(!1);
                k(s, q);
                b.each(m, function (a, d) {
                    (d.nodeType != 3 || d.nodeValue.length > 0) && d.parentNode.removeChild(d)
                });
                return q
            };
            this.cloneContents = function () {
                return this.extractContents(!1)
            };
            this.getText = function () {
                return this.cloneContents().innerHTML.replace(/<.*?>/g, "")
            }
        };
        b.cmd = function (a) {
            this.doc = b.g[a].iframeDoc;
            this.keSel = b.g[a].keSel;
            this.keRange = b.g[a].keRange;
            this.mergeAttributes = function (a, f) {
                for (var c = 0, e = f.length; c < e; c++)b.each(f[c], function (f, c) {
                    if (f.charAt(0) == ".") {
                        var k = b.util.getJsKey(f.substr(1));
                        a.style[k] = c
                    } else b.browser.IE && b.browser.VERSION < 8 && f == "class" && (f = "className"), a.setAttribute(f, c)
                });
                return a
            };
            this.wrapTextNode = function (a, f, c, e, g) {
                var i = a.nodeValue.length,
                    l = f == 0 && c == i, j = new b.range(this.doc);
                j.selectTextNode(a.parentNode);
                return l && a.parentNode.tagName == e.tagName && j.comparePoints("END_TO_END", this.keRange) <= 0 && j.comparePoints("START_TO_START", this.keRange) >= 0 ? (this.mergeAttributes(a.parentNode, g), a) : (e = e.cloneNode(!0), l ? (f = a.cloneNode(!0), e.appendChild(f), a.parentNode.replaceChild(e, a), f) : (l = a, f < c ? (f > 0 && (l = a.splitText(f)), c < i && l.splitText(c - f), f = l.cloneNode(!0), e.appendChild(f), l.parentNode.replaceChild(e, l), f) : (f < i ? (l = a.splitText(f), l.parentNode.insertBefore(e,
                    l)) : l.nextSibling ? l.parentNode.insertBefore(e, l.nextSibling) : l.parentNode.appendChild(e), e)))
            };
            this.wrap = function (d, f) {
                var f = f || [], c = this;
                this.keSel.focus();
                var e = b.$$(d, this.doc);
                this.mergeAttributes(e, f);
                var g = this.keRange, i = g.startNode, l = g.startPos, j = g.endNode, m = g.endPos, o = g.getParentElement();
                if (!b.util.inMarquee(o)) {
                    var n = !1;
                    b.eachNode(o, function (d) {
                        d == i && (n = !0);
                        if (d.nodeType == 1)if (d == i && d == j)return b.util.inArray(d.tagName.toLowerCase(), b.g[a].noEndTags) ? l > 0 ? d.parentNode.appendChild(e) : d.parentNode.insertBefore(e,
                            d) : d.appendChild(e), g.selectNode(e), !1; else if (d == i)g.setStart(d, 0); else {
                            if (d == j)return g.setEnd(d, 0), !1
                        } else if (d.nodeType == 3 && n)if (d == i && d == j)return d = c.wrapTextNode(d, l, m, e, f), g.selectNode(d), !1; else if (d == i)d = c.wrapTextNode(d, l, d.nodeValue.length, e, f), g.setStart(d, 0); else if (d == j)return d = c.wrapTextNode(d, 0, m, e, f), g.setEnd(d, d.nodeType == 1 ? 0 : d.nodeValue.length), !1; else c.wrapTextNode(d, 0, d.nodeValue.length, e, f);
                        return !0
                    });
                    this.keSel.addRange(g)
                }
            };
            this.getTopParent = function (a, f) {
                for (var c = null; f;)if (f =
                        f.parentNode, b.util.inArray(f.tagName.toLowerCase(), a))c = f; else break;
                return c
            };
            this.splitNodeParent = function (a, f, c) {
                var e = new b.range(this.doc);
                e.selectNode(a.firstChild);
                e.setEnd(f, c);
                f = e.extractContents();
                a.parentNode.insertBefore(f, a);
                return {left: f, right: a}
            };
            this.remove = function (a) {
                var f = this.keRange, c = f.startNode, e = f.startPos, g = f.endNode, i = f.endPos;
                this.keSel.focus();
                if (!b.util.inMarquee(f.getParentElement())) {
                    var l = f.getText().replace(/\s+/g, "") === "";
                    if (!l || b.browser.IE) {
                        var j = [];
                        b.each(a, function (a) {
                            a !=
                            "*" && j.push(a)
                        });
                        var m = this.getTopParent(j, c), o = this.getTopParent(j, g);
                        if (m) {
                            var n = this.splitNodeParent(m, c, e);
                            f.setStart(n.right, 0);
                            if (c == g && b.util.getNodeTextLength(n.right) > 0)f.selectNode(n.right), c = new b.range(this.doc), c.selectTextNode(n.left), e > 0 && (i -= c.endNode.nodeValue.length), c.selectTextNode(n.right), g = c.startNode
                        }
                        if (l)if (m = f.startNode, m.nodeType == 1) {
                            if (m.nodeName.toLowerCase() == "br")return;
                            f.selectNode(m)
                        } else return; else o && (e = this.splitNodeParent(o, g, i), f.setEnd(e.left, 0), m == o && f.setStart(e.left,
                            0));
                        o = function (a, d) {
                            if (d.charAt(0) == ".") {
                                var f = b.util.getJsKey(d.substr(1));
                                a.style[f] = ""
                            } else b.browser.IE && b.browser.VERSION < 8 && d == "class" && (d = "className"), a.removeAttribute(d)
                        };
                        e = f.getNodeList();
                        f.setTextStart(f.startNode, f.startPos);
                        f.setTextEnd(f.endNode, f.endPos);
                        i = 0;
                        for (l = e.length; i < l; i++)if (m = e[i], m.nodeType == 1) {
                            n = m.tagName.toLowerCase();
                            if (a[n]) {
                                n = a[n];
                                c = 0;
                                for (g = n.length; c < g; c++)if (n[c] == "*") {
                                    b.util.removeParent(m);
                                    break
                                } else {
                                    o(m, n[c]);
                                    var r = [];
                                    m.outerHTML ? (attrHash = b.util.getAttrList(m.outerHTML),
                                        b.each(attrHash, function (a, d) {
                                            r.push({name: a, value: d})
                                        })) : r = m.attributes;
                                    if (r.length == 0) {
                                        b.util.removeParent(m);
                                        break
                                    } else if (r[0].name == "style" && r[0].value === "") {
                                        b.util.removeParent(m);
                                        break
                                    }
                                }
                            }
                            if (a["*"]) {
                                n = a["*"];
                                c = 0;
                                for (g = n.length; c < g; c++)o(m, n[c])
                            }
                        }
                        try {
                            this.keSel.addRange(f)
                        } catch (s) {
                        }
                    }
                }
            }
        };
        b.format = {
            getUrl: function (a, d, f, e) {
                if (!d)return a;
                d = d.toLowerCase();
                if (!b.util.inArray(d, ["absolute", "relative", "domain"]))return a;
                f = f || location.protocol + "//" + location.host;
                if (e === c)var h = location.pathname.match(/^(\/.*)\//),
                    e = h ? h[1] : "";
                if (h = a.match(/^(\w+:\/\/[^\/]*)/)) {
                    if (h[1] !== f)return a
                } else if (a.match(/^\w+:/))return a;
                h = function (a) {
                    a = a.split("/");
                    paths = [];
                    for (var d = 0, b = a.length; d < b; d++) {
                        var f = a[d];
                        f == ".." ? paths.length > 0 && paths.pop() : f !== "" && f != "." && paths.push(f)
                    }
                    return "/" + paths.join("/")
                };
                a.match(/^\//) ? a = f + h(a.substr(1)) : a.match(/^\w+:\/\//) || (a = f + h(e + "/" + a));
                if (d == "relative")var g = function (d, b) {
                    if (a.substr(0, d.length) === d) {
                        for (var f = [], c = 0; c < b; c++)f.push("..");
                        c = ".";
                        f.length > 0 && (c += "/" + f.join("/"));
                        e == "/" && (c +=
                            "/");
                        return c + a.substr(d.length)
                    } else if (f = d.match(/^(.*)\//))return g(f[1], ++b)
                }, a = g(f + e, 0).substr(2); else d == "absolute" && a.substr(0, f.length) === f && (a = a.substr(f.length));
                return a
            }, getHtml: function (a, d, f) {
                var c = d ? !0 : !1, a = a.replace(/(<pre[^>]*>)([\s\S]*?)(<\/pre>)/ig, function (a, d, b, f) {
                    return d + b.replace(/<br[^>]*>/ig, "\n") + f
                }), e = {}, g = ["xx-small", "x-small", "small", "medium", "large", "x-large", "xx-large"];
                c && b.each(d, function (a, d) {
                    for (var f = a.split(","), c = 0, k = f.length; c < k; c++)e[f[c]] = b.util.arrayToHash(d)
                });
                var i = !1, l = b.util.arrayToHash(b.setting.noEndTags);
                b.util.arrayToHash(b.setting.inlineTags);
                var j = b.util.arrayToHash(b.setting.endlineTags), a = a.replace(/((?:\r\n|\n|\r)*)<(\/)?([\w\-:]+)((?:\s+|(?:\s+[\w\-:]+)|(?:\s+[\w\-:]+=[^\s"'<>]+)|(?:\s+[\w\-:]+="[^"]*")|(?:\s+[\w\-:]+='[^']*'))*)(\/)?>((?:\r\n|\n|\r)*)/g, function (a, d, m, o, u, w, p) {
                    var d = d || "", m = m || "", v = o.toLowerCase(), o = u || "", w = w ? " " + w : "", p = p || "";
                    v === "script" && m !== "" && (i = !1);
                    if (i)return a;
                    v === "script" && m === "" && (i = !0);
                    if (c && typeof e[v] == "undefined")return "";
                    w === "" && typeof l[v] != "undefined" && (w = " /");
                    if (v in j) {
                        if (m || w)p = "\n"
                    } else p && (p = " ");
                    v !== "script" && v !== "style" && (d = "");
                    if (v === "font") {
                        var t = {}, x = "", o = o.replace(/\s*([\w\-:]+)=([^\s"'<>]+|"[^"]*"|'[^']*')/g, function (a, d, b) {
                            d = d.toLowerCase();
                            b = (b || "").replace(/^["']|["']$/g, "");
                            if (d === "color")return t.color = b, " ";
                            if (d === "size")return t["font-size"] = g[parseInt(b) - 1] || "", " ";
                            if (d === "face")return t["font-family"] = b, " ";
                            if (d === "style")return x = b, " ";
                            return a
                        });
                        x && !/;$/.test(x) && (x += ";");
                        b.each(t, function (a,
                                            d) {
                            d !== "" && (/\s/.test(d) && (d = "'" + d + "'"), x += a + ":" + d + ";")
                        });
                        x && (o += ' style="' + x + '"');
                        v = "span"
                    }
                    return o !== "" ? (o = o.replace(/\s*([\w\-:]+)=([^\s"'<>]+|"[^"]*"|'[^']*')/g, function (a, d, g) {
                        a = d.toLowerCase();
                        g = g || "";
                        if (c && (a.charAt(0) === "." || a !== "style" && typeof e[v][a] == "undefined"))return " ";
                        if (g === "")g = '""'; else {
                            if (a === "style") {
                                g = g.substr(1, g.length - 2);
                                g = g.replace(/\s*([^\s]+?)\s*:(.*?)(;|$)/g, function (a, d, f) {
                                    a = d.toLowerCase();
                                    if (c && typeof e[v].style == "undefined" && typeof e[v]["." + a] == "undefined")return "";
                                    f = b.util.trim(f);
                                    f = b.util.rgbToHex(f);
                                    return a + ":" + f + ";"
                                });
                                g = b.util.trim(g);
                                if (g === "")return "";
                                g = '"' + g + '"'
                            }
                            b.util.inArray(a, ["src", "href"]) && (g.charAt(0) === '"' && (g = g.substr(1, g.length - 2)), g = b.format.getUrl(g, f));
                            g.charAt(0) !== '"' && (g = '"' + g + '"')
                        }
                        return " " + a + "=" + g + " "
                    }), o = o.replace(/\s+(checked|selected|disabled|readonly)(\s+|$)/ig, function (a, d) {
                        var b = d.toLowerCase();
                        if (c && (b.charAt(0) === "." || typeof e[v][b] == "undefined"))return " ";
                        return " " + b + '="' + b + '" '
                    }), o = b.util.trim(o), (o = o.replace(/\s+/g, " ")) &&
                    (o = " " + o), d + "<" + m + v + o + w + ">" + p) : d + "<" + m + v + w + ">" + p
                });
                b.browser.IE || (a = a.replace(/<p><br\s+\/>\n<\/p>/ig, "<p>&nbsp;</p>"), a = a.replace(/<br\s+\/>\n<\/p>/ig, "</p>"));
                var a = a.replace(/\u200B/g, ""), m = b.setting.inlineTags.join("|"), o = function (a) {
                    var d = a.replace(RegExp("<(" + m + ")[^>]*><\\/(" + m + ")>", "ig"), function (a, d, b) {
                        return d == b ? "" : a
                    });
                    a !== d && (d = o(d));
                    return d
                };
                return b.util.trim(o(a))
            }
        };
        b.attr = function (a, d, f) {
            b.browser.IE && b.browser.VERSION < 8 && d.toLowerCase() == "class" && (d = "className");
            f = "" + f;
            a.setAttribute(d,
                f);
            f === "" && a.removeAttribute(d)
        };
        b.addClass = function (a, d) {
            if (typeof a == "object") {
                var b = a.className;
                if (b) {
                    if ((" " + b + " ").indexOf(" " + d + " ") < 0)a.className = b + " " + d
                } else a.className = d
            } else typeof a == "string" && (a = /\s+class\s*=/.test(a) ? a.replace(/(\s+class=["']?)([^"']*)(["']?[\s>])/, function (a, b, f, c) {
                return (" " + f + " ").indexOf(" " + d + " ") < 0 ? f === "" ? b + d + c : b + f + " " + d + c : a
            }) : a.substr(0, a.length - 1) + ' class="' + d + '">');
            return a
        };
        b.removeClass = function (a, d) {
            var f = a.className || "", f = " " + f + " ", d = " " + d + " ";
            if (f.indexOf(d) >=
                0)f = b.util.trim(f.replace(RegExp(d, "ig"), "")), f === "" ? (f = a.getAttribute("class") ? "class" : "className", a.removeAttribute(f)) : a.className = f;
            return a
        };
        b.getComputedStyle = function (a, d) {
            var f = a.ownerDocument, c = f.parentWindow || f.defaultView, f = b.util.getJsKey(d), e = "";
            c.getComputedStyle ? (c = c.getComputedStyle(a, null), e = c[f] || c.getPropertyValue(d) || a.style[f]) : a.currentStyle && (e = a.currentStyle[f] || a.style[f]);
            return e
        };
        b.getCommonAncestor = function (a, d) {
            function f(a) {
                for (; a;) {
                    if (a.nodeType == 1 && a.tagName.toLowerCase() ===
                        d)return a;
                    a = a.parentNode
                }
                return null
            }

            var c = a.range, e = a.keRange, g = e.startNode, e = e.endNode;
            if (b.util.inArray(d, ["table", "td", "tr"]))b.browser.IE ? c.item ? c.item(0).nodeName.toLowerCase() === d && (g = e = c.item(0)) : (g = c.duplicate(), g.collapse(!0), c = c.duplicate(), c.collapse(!1), g = g.parentElement(), e = c.parentElement()) : (g = c.cloneRange(), g.collapse(!0), c = c.cloneRange(), c.collapse(!1), g = g.startContainer, e = c.startContainer);
            c = f(g);
            g = f(e);
            if (c && g && c === g)return c;
            return null
        };
        b.queryCommandValue = function (a, d) {
            function f() {
                var b =
                    a.queryCommandValue(d);
                typeof b !== "string" && (b = "");
                return b
            }

            var d = d.toLowerCase(), c = "";
            if (d === "fontname")c = f(), c = c.replace(/['"]/g, ""); else if (d === "formatblock") {
                c = f();
                if (c === "") {
                    var e = new b.selection(a), g = b.getCommonAncestor(e, "h1");
                    g || (g = b.getCommonAncestor(e, "h2"));
                    g || (g = b.getCommonAncestor(e, "h3"));
                    g || (g = b.getCommonAncestor(e, "h4"));
                    g || (g = b.getCommonAncestor(e, "p"));
                    if (g)c = g.nodeName
                }
                c === "Normal" && (c = "p")
            } else d === "fontsize" ? (e = new b.selection(a), (g = b.getCommonAncestor(e, "span")) && (c = b.getComputedStyle(g,
                "font-size"))) : d === "textcolor" ? (e = new b.selection(a), (g = b.getCommonAncestor(e, "span")) && (c = b.getComputedStyle(g, "color")), c = b.util.rgbToHex(c), c === "" && (c = "default")) : d === "bgcolor" && (e = new b.selection(a), (g = b.getCommonAncestor(e, "span")) && (c = b.getComputedStyle(g, "background-color")), c = b.util.rgbToHex(c), c === "" && (c = "default"));
            return c.toLowerCase()
        };
        b.util = {
            getDocumentElement: function (a) {
                a = a || document;
                return a.compatMode != "CSS1Compat" ? a.body : a.documentElement
            }, getDocumentHeight: function (a) {
                a = this.getDocumentElement(a);
                return Math.max(a.scrollHeight, a.clientHeight)
            }, getDocumentWidth: function (a) {
                a = this.getDocumentElement(a);
                return Math.max(a.scrollWidth, a.clientWidth)
            }, createTable: function (a) {
                a = b.$$("table", a);
                a.cellPadding = 0;
                a.cellSpacing = 0;
                a.border = 0;
                return {table: a, cell: a.insertRow(0).insertCell(0)}
            }, loadStyle: function (a) {
                var d = b.$$("link");
                d.setAttribute("type", "text/css");
                d.setAttribute("rel", "stylesheet");
                d.setAttribute("href", a);
                document.getElementsByTagName("head")[0].appendChild(d)
            }, getAttrList: function (a) {
                for (var d =
                    /\s+(?:([\w\-:]+)|(?:([\w\-:]+)=([\w\-:]+))|(?:([\w\-:]+)="([^"]*)")|(?:([\w\-:]+)='([^']*)'))(?=(?:\s|\/|>)+)/g, b, c, e = {}; b = d.exec(a);)c = b[1] || b[2] || b[4] || b[6], b = b[1] || (b[2] ? b[3] : b[4] ? b[5] : b[7]), e[c] = b;
                return e
            }, inArray: function (a, d) {
                for (var b = 0; b < d.length; b++)if (a == d[b])return !0;
                return !1
            }, trim: function (a) {
                return a.replace(/^\s+|\s+$/g, "")
            }, getJsKey: function (a) {
                for (var d = a.split("-"), a = "", b = 0, c = d.length; b < c; b++)a += b > 0 ? d[b].charAt(0).toUpperCase() + d[b].substr(1) : d[b];
                return a
            }, arrayToHash: function (a) {
                for (var d =
                {}, b = 0, c = a.length; b < c; b++)d[a[b]] = 1;
                return d
            }, escape: function (a) {
                a = a.replace(/&/g, "&amp;");
                a = a.replace(/</g, "&lt;");
                a = a.replace(/>/g, "&gt;");
                return a = a.replace(/"/g, "&quot;")
            }, unescape: function (a) {
                a = a.replace(/&lt;/g, "<");
                a = a.replace(/&gt;/g, ">");
                a = a.replace(/&quot;/g, '"');
                return a = a.replace(/&amp;/g, "&")
            }, getScrollPos: function () {
                var a, d;
                b.browser.IE || b.browser.OPERA ? (d = this.getDocumentElement(), a = d.scrollLeft, d = d.scrollTop) : (a = window.scrollX, d = window.scrollY);
                return {x: a, y: d}
            }, getElementPos: function (a) {
                var d =
                    0, b = 0;
                if (a.getBoundingClientRect)b = a.getBoundingClientRect(), a = this.getScrollPos(), d = b.left + a.x, b = b.top + a.y; else {
                    d = a.offsetLeft;
                    b = a.offsetTop;
                    for (a = a.offsetParent; a;)d += a.offsetLeft, b += a.offsetTop, a = a.offsetParent
                }
                return {x: d, y: b}
            }, getCoords: function (a) {
                a = a || window.event;
                return {x: a.clientX, y: a.clientY}
            }, setOpacity: function (a, d) {
                typeof a.style.opacity == "undefined" ? a.style.filter = d == 100 ? "" : "alpha(opacity=" + d + ")" : a.style.opacity = d == 100 ? "" : d / 100
            }, getIframeDoc: function (a) {
                return a.contentDocument || a.contentWindow.document
            },
            rgbToHex: function (a) {
                function d(a) {
                    a = parseInt(a).toString(16);
                    return a.length > 1 ? a : "0" + a
                }

                return a.replace(/rgb\s*?\(\s*?(\d+)\s*?,\s*?(\d+)\s*?,\s*?(\d+)\s*?\)/ig, function (a, b, c, e) {
                    return "#" + d(b) + d(c) + d(e)
                })
            }, parseJson: function (a) {
                var d;
                if (d = /\{[\s\S]*\}|\[[\s\S]*\]/.exec(a))a = d[0];
                d = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
                d.lastIndex = 0;
                d.test(a) && (a = a.replace(d, function (a) {
                    return "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
                }));
                if (/^[\],:{}\s]*$/.test(a.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, "")))return eval("(" + a + ")");
                throw"JSON parse error";
            }, getParam: function (a, d) {
                return a.match(RegExp("[?&]" + d + "=([^?&]+)", "i")) ? unescape(RegExp.$1) : ""
            }, createRange: function (a) {
                return a.body.createTextRange ? a.body.createTextRange() : a.createRange()
            }, getNodeType: function (a) {
                return a.nodeType == 1 && b.util.inArray(a.tagName.toLowerCase(),
                    b.setting.noEndTags) ? 88 : a.nodeType
            }, inMarquee: function (a) {
                for (var d; a;) {
                    d = a.nodeName.toLowerCase();
                    if (d == "marquee" || d == "select")return !0;
                    a = a.parentNode
                }
                return !1
            }, moveToElementText: function (a, d) {
                this.inMarquee(d) || a.moveToElementText(d)
            }, getNodeTextLength: function (a) {
                var d = b.util.getNodeType(a);
                if (d == 1)return a.innerHTML.replace(/<.*?>/ig, "").length; else if (d == 3)return a.nodeValue.length
            }, getNodeStartRange: function (a, d) {
                var c = b.util.createRange(a), e = d.nodeType;
                if (e == 1)return b.util.moveToElementText(c,
                    d), c; else if (e == 3) {
                    for (var e = 0, h = d.previousSibling; h;) {
                        if (h.nodeType == 1) {
                            var g = b.util.createRange(a);
                            b.util.moveToElementText(g, h);
                            c.setEndPoint("StartToEnd", g);
                            c.moveStart("character", e);
                            return c
                        } else h.nodeType == 3 && (e += h.nodeValue.length);
                        h = h.previousSibling
                    }
                    b.util.moveToElementText(c, d.parentNode);
                    c.moveStart("character", e);
                    return c
                }
            }, removeParent: function (a) {
                if (a.hasChildNodes)for (var d = a.firstChild; d;) {
                    var b = d.nextSibling;
                    a.parentNode.insertBefore(d, a);
                    d = b
                }
                a.parentNode.removeChild(a)
            }, pluginLang: function (a,
                                     d) {
                b.each(b.lang.plugins[a], function (a, c) {
                    var e = b.$("lang." + a, d);
                    e && (e.parentNode.insertBefore(d.createTextNode(c), e), e.parentNode.removeChild(e))
                })
            }, drag: function (a, d, c, e) {
                var h = b.g[a];
                d.onmousedown = function (a) {
                    function d(a) {
                        if (t) {
                            var c = b.util.getCoords(a), f = b.util.getScrollPos(), a = parseInt(c.y - u - p + f.y), c = parseInt(c.x - w - v + f.x);
                            e(n, r, s, q, a, c)
                        }
                    }

                    function l(a) {
                        if (t) {
                            var d = b.util.getCoords(a, h.iframeDoc), a = parseInt(x.y + d.y - u - p), d = parseInt(x.x + d.x - w - v);
                            e(n, r, s, q, a, d)
                        }
                    }

                    function j(a) {
                        t = !1;
                        m.releaseCapture &&
                        m.releaseCapture();
                        b.event.remove(document, "mousemove", d);
                        b.event.remove(document, "mouseup", j);
                        b.event.remove(h.iframeDoc, "mousemove", l);
                        b.event.remove(h.iframeDoc, "mouseup", j);
                        b.event.remove(document, "selectstart", z);
                        b.event.stop(a);
                        return !1
                    }

                    var m = this, a = a || window.event, o = b.util.getCoords(a), n = parseInt(c.style.top), r = parseInt(c.style.left), s = c.style.width, q = c.style.height;
                    s.match(/%$/) && (s = c.offsetWidth + "px");
                    q.match(/%$/) && (q = c.offsetHeight + "px");
                    var s = parseInt(s), q = parseInt(q), u = o.y, w = o.x, o = b.util.getScrollPos(),
                        p = o.y, v = o.x, t = !0, x = b.util.getElementPos(h.iframe), z = function () {
                            return !1
                        };
                    b.event.add(document, "mousemove", d);
                    b.event.add(document, "mouseup", j);
                    b.event.add(h.iframeDoc, "mousemove", l);
                    b.event.add(h.iframeDoc, "mouseup", j);
                    b.event.add(document, "selectstart", z);
                    m.setCapture && m.setCapture();
                    b.event.stop(a);
                    return !1
                }
            }, resize: function (a, d, c, e, h) {
                h = typeof h == "undefined" ? !0 : h;
                a = b.g[a];
                if (a.container && (!e || !(parseInt(d) <= a.minWidth || parseInt(c) <= a.minHeight))) {
                    if (h)a.container.style.width = d;
                    a.container.style.height =
                        c;
                    d = parseInt(c) - a.toolbarHeight - a.statusbarHeight;
                    if (d >= 0)a.iframe.style.height = d + "px", a.newTextarea.style.height = ((b.browser.IE && b.browser.VERSION < 8 || document.compatMode != "CSS1Compat") && d >= 2 ? d - 2 : d) + "px"
                }
            }, hideLoadingPage: function (a) {
                a = b.g[a].dialogStack;
                a = a[a.length - 1];
                a.loading.style.display = "none";
                a.iframe.style.display = ""
            }, showLoadingPage: function (a) {
                a = b.g[a].dialogStack;
                a = a[a.length - 1];
                a.loading.style.display = "";
                a.iframe.style.display = "none"
            }, setDefaultPlugin: function () {
                for (var a = ["selectall",
                    "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "bold", "italic", "underline", "strikethrough"], d = {
                    bold: "B",
                    italic: "I",
                    underline: "U"
                }, c = 0; c < a.length; c++) {
                    var e = a[c], h = {};
                    if (e in d)h.init = function (a) {
                        return function (c) {
                            b.event.ctrl(b.g[c].iframeDoc, d[a], function () {
                                b.plugin[a].click(c);
                                b.util.focus(c)
                            }, c)
                        }
                    }(e);
                    h.click = function (a) {
                        return function (d) {
                            b.util.execCommand(d, a, null)
                        }
                    }(e);
                    b.plugin[e] = h
                }
            }, getFullHtml: function (a) {
                var d =
                    "<html>";
                d += "<head>";
                d += '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />';
                d += "<title>KindEditor</title>";
                d += '<link href="' + b.g[a].skinsPath + "common/editor.css?ver=" + escape(b.version) + '" rel="stylesheet" type="text/css" />';
                a = b.g[a].cssPath;
                typeof a == "string" && (a = [a]);
                for (var c = 0, e = a.length; c < e; c++)a[c] !== "" && (d += '<link href="' + a[c] + '" rel="stylesheet" type="text/css" />');
                d += "</head>";
                d += '<body class="ke-content"></body>';
                d += "</html>";
                return d
            }, getMediaType: function (a) {
                return a.match(/\.(rm|rmvb)(\?|$)/i) ?
                    "rm" : a.match(/\.(swf|flv)(\?|$)/i) ? "flash" : "media"
            }, getMediaImage: function (a, d, c) {
                var e = c.width, h = c.height, d = d || this.getMediaType(c.src), c = this.getMediaEmbed(c), g = "";
                e > 0 && (g += "width:" + e + "px;");
                h > 0 && (g += "height:" + h + "px;");
                a = '<img class="ke-' + d + '" src="' + b.g[a].skinsPath + 'common/blank.gif" ';
                g !== "" && (a += 'style="' + g + '" ');
                a += 'kesrctag="' + escape(c) + '" alt="" />';
                return a
            }, getMediaEmbed: function (a) {
                var d = "<embed ";
                b.each(a, function (a, b) {
                    d += a + '="' + b + '" '
                });
                d += "/>";
                return d
            }, execGetHtmlHooks: function (a,
                                           d) {
                for (var c = b.g[a].getHtmlHooks, e = 0, h = c.length; e < h; e++)d = c[e](d);
                return d
            }, execSetHtmlHooks: function (a, d) {
                for (var c = b.g[a].setHtmlHooks, e = 0, h = c.length; e < h; e++)d = c[e](d);
                return d
            }, execOnchangeHandler: function (a) {
                for (var a = b.g[a].onchangeHandlerStack, d = 0, c = a.length; d < c; d++)a[d]()
            }, toData: function (a, d) {
                var c = b.g[a], e = this.execGetHtmlHooks(a, d), e = e.replace(/^\s*<br[^>]*>\s*$/ig, ""), e = e.replace(/^\s*<p>\s*&nbsp;\s*<\/p>\s*$/ig, "");
                return c.filterMode ? b.format.getHtml(e, c.htmlTags, c.urlType) : b.format.getHtml(e,
                    null, c.urlType)
            }, getData: function (a, d) {
                var f = b.g[a];
                (d = d === c ? f.wyswygMode : d) || this.innerHtml(f.iframeDoc.body, b.util.execSetHtmlHooks(a, f.newTextarea.value));
                return this.toData(a, f.iframeDoc.body.innerHTML)
            }, getSrcData: function (a) {
                var d = b.g[a];
                d.wyswygMode || this.innerHtml(d.iframeDoc.body, b.util.execSetHtmlHooks(a, d.newTextarea.value));
                return d.iframeDoc.body.innerHTML
            }, getPureData: function (a) {
                return this.extractText(this.getData(a))
            }, extractText: function (a) {
                a = a.replace(/<(?!img|embed).*?>/ig, "");
                return a = a.replace(/&nbsp;/ig, " ")
            }, isEmpty: function (a) {
                return this.getPureData(a).replace(/\r\n|\n|\r/, "").replace(/^\s+|\s+$/, "") === ""
            }, setData: function (a) {
                var d = b.g[a];
                if (d.srcTextarea)d.srcTextarea.value = this.getData(a)
            }, focus: function (a) {
                a = b.g[a];
                a.wyswygMode ? a.iframeWin.focus() : a.newTextarea.focus()
            }, click: function (a, d) {
                this.focus(a);
                b.hideMenu(a);
                b.plugin[d].click(a)
            }, selection: function (a) {
                (!b.browser.IE || !b.g[a].keRange) && this.setSelection(a)
            }, setSelection: function (a) {
                var a = b.g[a], d = new b.selection(a.iframeDoc);
                if (!b.browser.IE || d.range.item || d.range.parentElement().ownerDocument === a.iframeDoc)a.keSel = d, a.keRange = a.keSel.keRange, a.sel = a.keSel.sel, a.range = a.keSel.range
            }, select: function (a) {
                b.browser.IE && b.g[a].wyswygMode && b.g[a].range && b.g[a].range.select()
            }, execCommand: function (a, d, c) {
                b.util.focus(a);
                b.util.select(a);
                try {
                    b.g[a].iframeDoc.execCommand(d, !1, c)
                } catch (e) {
                }
                b.toolbar.updateState(a);
                b.util.execOnchangeHandler(a)
            }, innerHtml: function (a, d) {
                if (b.browser.IE) {
                    a.innerHTML = '<img id="__ke_temp_tag__" width="0" height="0" />' +
                    d;
                    var c = b.$("__ke_temp_tag__", a.ownerDocument);
                    c && c.parentNode.removeChild(c)
                } else a.innerHTML = d
            }, pasteHtml: function (a, d, c) {
                a = b.g[a];
                c ? d = '<img id="__ke_temp_tag__" width="0" height="0" />' + d : d += '<img id="__ke_temp_tag__" width="0" height="0" />';
                b.browser.IE ? a.range.item ? a.range.item(0).outerHTML = d : a.range.pasteHTML(d) : (a.range.deleteContents(), d = a.range.createContextualFragment(d), a.range.insertNode(d));
                d = b.$("__ke_temp_tag__", a.iframeDoc);
                c = a.iframeDoc.createTextNode("");
                d.parentNode.replaceChild(c,
                    d);
                a.keRange.selectNode(c);
                a.keSel.addRange(a.keRange)
            }, insertHtml: function (a, d) {
                if (d !== "") {
                    var c = b.g[a];
                    if (c.wyswygMode && c.range) {
                        d = this.execSetHtmlHooks(a, d);
                        if (b.browser.IE)if (this.select(a), c.range.item)try {
                            c.range.item(0).outerHTML = d
                        } catch (e) {
                            var c = c.range.item(0), h = c.parentNode;
                            h.removeChild(c);
                            if (h.nodeName.toLowerCase() != "body")h = h.parentNode;
                            this.innerHtml(h, d + h.innerHTML)
                        } else c.range.pasteHTML('<span id="__ke_temp_tag__">\u200b</span>' + d), c = b.$("__ke_temp_tag__", c.iframeDoc), c.parentNode.removeChild(c);
                        else if (b.browser.GECKO) {
                            this.execCommand(a, "inserthtml", d);
                            return
                        } else this.pasteHtml(a, d);
                        b.util.execOnchangeHandler(a)
                    }
                }
            }, setFullHtml: function (a, d) {
                var c = b.g[a];
                !b.browser.IE && d === "" && (d = "<br />");
                d = b.util.execSetHtmlHooks(a, d);
                this.innerHtml(c.iframeDoc.body, d);
                if (!c.wyswygMode)c.newTextarea.value = b.util.getData(a, !0);
                b.util.execOnchangeHandler(a)
            }, selectImageWebkit: function (a, d, c) {
                if (b.browser.WEBKIT && (d = d.srcElement || d.target, d.tagName.toLowerCase() == "img"))c && b.util.selection(a), c = b.g[a].keRange,
                    c.selectNode(d), b.g[a].keSel.addRange(c)
            }, addTabEvent: function (a) {
                var d = b.g[a];
                b.event.add(d.iframeDoc, "keydown", function (c) {
                    if (c.keyCode == 9)return d.afterTab && d.afterTab(a), b.event.stop(c), !1
                }, a)
            }, addContextmenuEvent: function (a) {
                var d = b.g[a];
                d.contextmenuItems.length != 0 && d.useContextmenu && b.event.add(d.iframeDoc, "contextmenu", function (c) {
                    b.hideMenu(a);
                    b.util.setSelection(a);
                    b.util.selectImageWebkit(a, c, !1);
                    for (var e = 0, h = [], g = 0, i = d.contextmenuItems.length; g < i; g++) {
                        var l = d.contextmenuItems[g];
                        if (l ===
                            "-")h.push(l); else if (l.cond && l.cond(a) && (h.push(l), l.options)) {
                            var j = parseInt(l.options.width) || 0;
                            j > e && (e = j)
                        }
                    }
                    for (; h.length > 0 && h[0] === "-";)h.shift();
                    for (; h.length > 0 && h[h.length - 1] === "-";)h.pop();
                    l = null;
                    g = 0;
                    for (i = h.length; g < i; g++)h[g] === "-" && l === "-" && delete h[g], l = h[g] || null;
                    if (h.length > 0) {
                        for (var m = new b.menu({
                            id: a,
                            event: c,
                            type: "contextmenu",
                            width: e
                        }), g = 0, i = h.length; g < i; g++)(l = h[g]) && (l === "-" ? g < i - 1 && m.addSeparator() : m.add(l.text, function (d) {
                            return function () {
                                d.click(a, m)
                            }
                        }(l), l.options));
                        m.show();
                        b.event.stop(c);
                        return !1
                    }
                    return !0
                }, a)
            }, addNewlineEvent: function (a) {
                var d = b.g[a];
                if (!(b.browser.IE && d.newlineTag.toLowerCase() != "br") && (!b.browser.GECKO || !(b.browser.VERSION < 3 && d.newlineTag.toLowerCase() != "p")))b.browser.OPERA || b.event.add(d.iframeDoc, "keydown", function (c) {
                    if (c.keyCode != 13 || c.shiftKey || c.ctrlKey || c.altKey)return !0;
                    b.util.setSelection(a);
                    var e = d.keRange.getParentElement();
                    if (!b.util.inMarquee(e)) {
                        e = e.tagName.toLowerCase();
                        if (d.newlineTag.toLowerCase() == "br") {
                            if (!b.util.inArray(e, ["h1",
                                    "h2", "h3", "h4", "h5", "h6", "li"])) {
                                b.util.pasteHtml(a, "<br />");
                                e = d.keRange.startNode.nextSibling;
                                if (b.browser.IE)e || b.util.pasteHtml(a, "<br />", !0); else if (b.browser.WEBKIT)if (e) {
                                    var h = new b.range(d.iframeDoc);
                                    h.selectNode(e.parentNode);
                                    h.setStart(e, 0);
                                    h.cloneContents().innerHTML.replace(/<(?!img|embed).*?>/ig, "") === "" && b.util.pasteHtml(a, "<br />", !0)
                                } else b.util.pasteHtml(a, "<br />", !0);
                                b.event.stop(c);
                                return !1
                            }
                        } else b.util.inArray(e, ["p", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "div", "li"]) || b.util.execCommand(a,
                            "formatblock", "<P>");
                        return !0
                    }
                }, a)
            }
        };
        b.layout = {
            hide: function (a) {
                var d = b.g[a];
                b.hideMenu(a);
                for (a = d.dialogStack; a.length > 0;)a[a.length - 1].hide();
                d.maskDiv.style.display = "none"
            }
        };
        b.hideMenu = function (a) {
            a = b.g[a];
            a.hideDiv.innerHTML = "";
            a.hideDiv.style.display = "none"
        };
        b.colorpicker = function (a) {
            var d, c = a.x || 0, e = a.y || 0, h = a.z || 0, g = a.colors || b.setting.colorTable, i = a.doc || document, l = a.onclick, j = (a.selectedColor || "").toLowerCase();
            d = b.$$("div");
            d.className = "ke-colorpicker";
            d.style.top = e + "px";
            d.style.left = c +
            "px";
            d.style.zIndex = h;
            this.remove = function () {
                i.body.removeChild(d)
            };
            this.getElement = function () {
                function a(d, c, e) {
                    j === c.toLowerCase() && (e += " ke-colorpicker-cell-selected");
                    d.className = e;
                    d.title = c || b.lang.noColor;
                    d.onmouseover = function () {
                        this.className = e + " ke-colorpicker-cell-on"
                    };
                    d.onmouseout = function () {
                        this.className = e
                    };
                    d.onclick = function () {
                        l(c)
                    };
                    if (c) {
                        var f = b.$$("div");
                        f.className = "ke-colorpicker-cell-color";
                        f.style.backgroundColor = c;
                        d.appendChild(f)
                    } else d.innerHTML = b.lang.noColor
                }

                var d = b.$$("table");
                d.className = "ke-colorpicker-table";
                d.cellPadding = 0;
                d.cellSpacing = 0;
                d.border = 0;
                var c = d.insertRow(0), e = c.insertCell(0);
                e.colSpan = g[0].length;
                a(e, "", "ke-colorpicker-cell-top");
                for (var f = 0; f < g.length; f++)for (var c = d.insertRow(f + 1), h = 0; h < g[f].length; h++) {
                    var k = g[f][h], e = c.insertCell(h);
                    a(e, k, "ke-colorpicker-cell")
                }
                return d
            };
            this.create = function () {
                d.appendChild(this.getElement());
                b.event.bind(d, "click", function () {
                });
                b.event.bind(d, "mousedown", function () {
                });
                i.body.appendChild(d)
            }
        };
        b.menu = function (a) {
            function d(d,
                       c) {
                var e = a.id, g = 0, i = 0;
                this.type == "menu" ? (e = b.g[e].toolbarIcon[a.cmd], i = b.util.getElementPos(e[0]), g = i.x, i = i.y + e[0].offsetHeight) : (i = b.util.getCoords(a.event), e = b.util.getElementPos(b.g[e].iframe), g = i.x + e.x, i = i.y + e.y + 5);
                if (d > 0 || c > 0) {
                    var e = b.util.getScrollPos(), l = b.util.getDocumentElement(), e = e.x + l.clientWidth - d - 2;
                    g > e && (g = e)
                }
                return {x: g, y: i}
            }

            (function () {
                var c = a.width;
                this.type = a.type && a.type == "contextmenu" ? a.type : "menu";
                var e = b.$$("div");
                e.className = "ke-" + this.type;
                e.setAttribute("name", a.cmd);
                var h =
                    d.call(this, 0, 0);
                e.style.top = h.y + "px";
                e.style.left = h.x + "px";
                if (a.width)e.style.width = /^\d+$/.test(c) ? c + "px" : c;
                b.event.bind(e, "click", function () {
                }, a.id);
                b.event.bind(e, "mousedown", function () {
                }, a.id);
                this.div = e
            }).call(this);
            this.add = function (a, d, e) {
                var g, i, l = !1;
                if (e !== c)g = e.height, i = e.iconHtml, l = e.checked;
                var j = this, e = b.$$("div");
                e.className = "ke-" + j.type + "-item";
                if (g)e.style.height = g;
                var m = b.$$("div");
                m.className = "ke-" + this.type + "-left";
                var o = b.$$("div");
                o.className = "ke-" + j.type + "-center";
                if (g)o.style.height =
                    g;
                var n = b.$$("div");
                n.className = "ke-" + this.type + "-right";
                if (g)n.style.lineHeight = g;
                e.onmouseover = function () {
                    this.className = "ke-" + j.type + "-item ke-" + j.type + "-item-on";
                    o.className = "ke-" + j.type + "-center ke-" + j.type + "-center-on"
                };
                e.onmouseout = function () {
                    this.className = "ke-" + j.type + "-item";
                    o.className = "ke-" + j.type + "-center"
                };
                e.onclick = d;
                e.appendChild(m);
                e.appendChild(o);
                e.appendChild(n);
                l ? b.util.innerHtml(m, '<span class="ke-common-icon ke-common-icon-url ke-icon-checked"></span>') : i && b.util.innerHtml(m,
                    i);
                b.util.innerHtml(n, a);
                this.append(e)
            };
            this.addSeparator = function () {
                var a = b.$$("div");
                a.className = "ke-" + this.type + "-separator";
                this.append(a)
            };
            this.append = function (a) {
                this.div.appendChild(a)
            };
            this.insert = function (a) {
                b.util.innerHtml(this.div, a)
            };
            this.hide = function () {
                b.hideMenu(a.id)
            };
            this.show = function () {
                this.hide();
                var c = a.id;
                b.g[c].hideDiv.style.display = "";
                b.g[c].hideDiv.appendChild(this.div);
                c = d.call(this, this.div.clientWidth, this.div.clientHeight);
                this.div.style.top = c.y + "px";
                this.div.style.left =
                    c.x + "px"
            };
            this.picker = function (d) {
                this.append((new b.colorpicker({
                    colors: b.g[a.id].colorTable, onclick: function (d) {
                        b.plugin[a.cmd].exec(a.id, d)
                    }, selectedColor: d
                })).getElement());
                this.show()
            }
        };
        b.button = function (a) {
            a = a || {};
            doc = a.doc || document;
            var d = b.$$("span", doc);
            d.className = "ke-button-common ke-button-outer " + (a.className || "");
            d.title = a.text;
            btn = b.$$("input", doc);
            btn.className = "ke-button-common ke-button";
            btn.type = "button";
            btn.value = a.text || "";
            if (a.clickFn)btn.onclick = a.clickFn;
            d.appendChild(btn);
            return {span: d, btn: btn}
        };
        b.dialog = function (a) {
            function d() {
                b.util.getDocumentElement();
                var a = b.util.getScrollPos();
                k = a.y;
                h = a.x
            }

            function e() {
                var d = this.width + this.widthMargin, c = this.height + this.heightMargin, f = a.id, h = b.g[f], k = 0, o = 0;
                h.dialogAlignType == "page" ? (h = b.util.getDocumentElement(), f = b.util.getScrollPos(), k = Math.round(f.x + (h.clientWidth - d) / 2), o = Math.round(f.y + (h.clientHeight - c) / 2)) : (f = b.util.getElementPos(b.g[f].container), h = h.container, k = Math.round(h.clientWidth / 2) - Math.round(d / 2), c = Math.round(h.clientHeight /
                2) - Math.round(c / 2), k = k < 0 ? f.x : f.x + k, o = c < 0 ? f.y : f.y + c);
                return {x: k < 0 ? 0 : k, y: o < 0 ? 0 : o}
            }

            this.widthMargin = 30;
            this.heightMargin = 100;
            this.zIndex = 19811214;
            this.width = a.width;
            this.height = a.height;
            var k, h;
            this.beforeHide = a.beforeHide;
            this.afterHide = a.afterHide;
            this.beforeShow = a.beforeShow;
            this.afterShow = a.afterShow;
            this.ondrag = a.ondrag;
            this.resize = function (a, d) {
                if (a)this.width = a;
                if (d)this.height = d;
                this.hide();
                this.show()
            };
            this.hide = function () {
                this.beforeHide && this.beforeHide(c);
                var c = a.id, e = b.g[c].dialogStack;
                if (e[e.length - 1] == this) {
                    var f = e.pop().iframe;
                    f.src = "javascript:false";
                    f.parentNode.removeChild(f);
                    document.body.removeChild(this.div);
                    if (e.length < 1)b.g[c].maskDiv.style.display = "none";
                    b.event.remove(window, "resize", d);
                    b.event.remove(window, "scroll", d);
                    this.afterHide && this.afterHide(c);
                    b.util.focus(c)
                }
            };
            this.show = function () {
                this.beforeShow && this.beforeShow(i);
                var g = this, i = a.id, l = b.$$("div");
                l.className = "ke-dialog";
                b.event.bind(l, "click", function () {
                }, i);
                var j = b.g[i].dialogStack;
                if (j.length > 0)this.zIndex =
                    j[j.length - 1].zIndex + 1;
                l.style.zIndex = this.zIndex;
                j = e.call(this);
                l.style.top = j.y + "px";
                l.style.left = j.x + "px";
                b.g[i].shadowMode ? b.addClass(l, "ke-dialog-shadow") : b.addClass(l, "ke-dialog-no-shadow");
                j = b.$$("div");
                j.className = "ke-dialog-title";
                j.innerHTML = a.title;
                var m = b.$$("span");
                m.className = "ke-dialog-close";
                m.alt = b.lang.close;
                m.title = b.lang.close;
                m.onclick = function () {
                    g.hide();
                    b.util.select(i)
                };
                j.appendChild(m);
                d();
                b.event.add(window, "resize", d);
                b.event.add(window, "scroll", d);
                b.util.drag(i, j, l, function (a,
                                               c, b, e, f, j) {
                    if (g.ondrag)g.ondrag(i);
                    d();
                    f = a + f;
                    j = c + j;
                    f < k && (f = k);
                    j < h && (j = h);
                    l.style.top = f + "px";
                    l.style.left = j + "px"
                });
                l.appendChild(j);
                var o = b.$$("div");
                o.className = "ke-dialog-body";
                j = b.util.createTable();
                j.table.className = "ke-loading-table";
                j.table.style.width = this.width + "px";
                j.table.style.height = this.height + "px";
                m = b.$$("span");
                m.className = "ke-loading-img";
                j.cell.appendChild(m);
                m = b.g[i].dialogStack.length == 0 && b.g[i].dialog ? b.g[i].dialog : b.$$("iframe");
                m.className = a.useFrameCSS ? "ke-dialog-iframe ke-dialog-iframe-border" :
                    "ke-dialog-iframe";
                m.setAttribute("frameBorder", "0");
                m.style.width = this.width + "px";
                m.style.height = this.height + "px";
                m.style.display = "none";
                o.appendChild(m);
                o.appendChild(j.table);
                l.appendChild(o);
                var n = b.$$("div");
                n.className = "ke-dialog-bottom";
                var r = o = null, s = null;
                if (a.previewButton) {
                    var q = b.button({
                        className: "ke-dialog-preview", text: a.previewButton, clickFn: function () {
                            var d = b.g[i].dialogStack;
                            d[d.length - 1] == g && (a.previewClickFn ? a.previewClickFn(i) : b.plugin[a.cmd].preview(i))
                        }
                    }), s = q.btn;
                    n.appendChild(q.span)
                }
                if (a.yesButton)q =
                    b.button({
                        className: "ke-dialog-yes", text: a.yesButton, clickFn: function () {
                            var d = b.g[i].dialogStack;
                            d[d.length - 1] == g && (a.yesClickFn ? a.yesClickFn(i) : b.plugin[a.cmd].exec(i))
                        }
                    }), r = q.btn, n.appendChild(q.span);
                if (a.noButton)q = b.button({
                    className: "ke-dialog-no", text: a.noButton, clickFn: function () {
                        g.hide();
                        b.util.select(i)
                    }
                }), o = q.btn, n.appendChild(q.span);
                (a.yesButton || a.noButton || a.previewButton) && l.appendChild(n);
                document.body.appendChild(l);
                b.event.bind(l, "mousedown", function () {
                }, i);
                window.focus();
                a.html !==
                c ? (n = b.util.getIframeDoc(m), q = b.util.getFullHtml(i), n.open(), n.write(q), n.close(), b.util.innerHtml(n.body, a.html)) : a.url !== c ? m.src = a.url : (n = "id=" + escape(i) + "&ver=" + escape(b.version), a.file === c ? m.src = b.g[i].pluginsPath + a.cmd + ".html?" + n : (n = (/\?/.test(a.file) ? "&" : "?") + n, m.src = b.g[i].pluginsPath + a.file + n));
                b.g[i].maskDiv.style.width = b.util.getDocumentWidth() + "px";
                b.g[i].maskDiv.style.height = b.util.getDocumentHeight() + "px";
                b.g[i].maskDiv.style.display = "block";
                this.iframe = m;
                this.loading = j.table;
                this.noButton =
                    o;
                this.yesButton = r;
                this.previewButton = s;
                this.div = l;
                b.g[i].dialogStack.push(this);
                b.g[i].dialog = m;
                b.g[i].yesButton = r;
                b.g[i].noButton = o;
                b.g[i].previewButton = s;
                a.loadingMode || b.util.hideLoadingPage(i);
                this.afterShow && this.afterShow(i);
                b.g[i].afterDialogCreate && b.g[i].afterDialogCreate(i)
            }
        };
        b.toolbar = {
            updateState: function (a) {
                for (var d = ["justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "bold", "italic", "underline",
                    "strikethrough"], c = 0; c < d.length; c++) {
                    var e = d[c], h = !1;
                    try {
                        h = b.g[a].iframeDoc.queryCommandState(e)
                    } catch (g) {
                    }
                    h ? b.toolbar.select(a, e) : b.toolbar.unselect(a, e)
                }
            }, isSelected: function (a, d) {
                return b.plugin[d] && b.plugin[d].isSelected ? !0 : !1
            }, select: function (a, d) {
                if (b.g[a].toolbarIcon[d]) {
                    var c = b.g[a].toolbarIcon[d][0];
                    c.className = "ke-icon ke-icon-selected";
                    c.onmouseover = null;
                    c.onmouseout = null
                }
            }, unselect: function (a, d) {
                if (b.g[a].toolbarIcon[d]) {
                    var c = b.g[a].toolbarIcon[d][0];
                    c.className = "ke-icon";
                    c.onmouseover =
                        function () {
                            this.className = "ke-icon ke-icon-on"
                        };
                    c.onmouseout = function () {
                        this.className = "ke-icon"
                    }
                }
            }, _setAttr: function (a, d, e) {
                d.className = "ke-icon";
                d.href = "javascript:;";
                d.onclick = function (d) {
                    var d = d || window.event, h = b.g[a].hideDiv.firstChild;
                    h && h.getAttribute("name") == e ? b.hideMenu(a) : b.util.click(a, e);
                    d.preventDefault && d.preventDefault();
                    d.stopPropagation && d.stopPropagation();
                    if (d.cancelBubble !== c)d.cancelBubble = !0;
                    return !1
                };
                d.onmouseover = function () {
                    this.className = "ke-icon ke-icon-on"
                };
                d.onmouseout =
                    function () {
                        this.className = "ke-icon"
                    };
                d.hidefocus = !0;
                d.title = b.lang[e]
            }, able: function (a, d) {
                var c = this;
                b.each(b.g[a].toolbarIcon, function (e, h) {
                    if (!b.util.inArray(e, d)) {
                        var g = h[1];
                        c._setAttr(a, h[0], e);
                        b.util.setOpacity(g, 100)
                    }
                })
            }, disable: function (a, d) {
                b.each(b.g[a].toolbarIcon, function (a, c) {
                    if (!b.util.inArray(a, d)) {
                        var e = c[0], g = c[1];
                        e.className = "ke-icon ke-icon-disabled";
                        b.util.setOpacity(g, 50);
                        e.onclick = null;
                        e.onmouseover = null;
                        e.onmouseout = null
                    }
                })
            }, create: function (a) {
                var d = b.util.arrayToHash(b.setting.items);
                b.g[a].toolbarIcon = [];
                var c = b.util.createTable(), e = c.table;
                e.className = "ke-toolbar";
                e.oncontextmenu = function () {
                    return !1
                };
                e.onmousedown = function () {
                    return !1
                };
                e.onmousemove = function () {
                    return !1
                };
                var c = c.cell, h = b.g[a].items.length, g = 0, i;
                b.g[a].toolbarHeight = b.g[a].toolbarLineHeight;
                for (var l = 0; l < h; l++) {
                    var j = b.g[a].items[l];
                    if (l == 0 || j == "-") {
                        var m = b.$$("table");
                        m.cellPadding = 0;
                        m.cellSpacing = 0;
                        m.border = 0;
                        m.className = "ke-toolbar-table";
                        i = m.insertRow(0);
                        g = 0;
                        c.appendChild(m);
                        if (j == "-") {
                            b.g[a].toolbarHeight +=
                                b.g[a].toolbarLineHeight;
                            continue
                        }
                    }
                    m = i.insertCell(g);
                    m.hideforcus = !0;
                    g++;
                    if (j == "|")j = b.$$("div"), j.className = "ke-toolbar-separator", m.appendChild(j); else {
                        var o = b.$$("a");
                        o.tabIndex = -1;
                        this._setAttr(a, o, j);
                        var n = b.$$("span");
                        n.className = typeof d[j] == "undefined" ? "ke-common-icon ke-icon-" + j : "ke-common-icon ke-common-icon-url ke-icon-" + j;
                        o.appendChild(n);
                        m.appendChild(o);
                        b.g[a].toolbarIcon[j] = [o, n];
                        b.toolbar.isSelected(a, j) && b.toolbar.select(a, j)
                    }
                }
                return e
            }
        };
        b.history = {
            addStackData: function (a, d) {
                var c =
                    "";
                a.length > 0 && (c = a[a.length - 1]);
                (a.length == 0 || d !== c) && a.push(d)
            }, add: function (a, d) {
                var c = b.g[a], e = b.util.getSrcData(a);
                c.undoStack.length > 0 && Math.abs(e.length - c.undoStack[c.undoStack.length - 1].length) < d || this.addStackData(c.undoStack, e)
            }, undo: function (a) {
                var d = b.g[a];
                if (d.undoStack.length != 0) {
                    var c = b.util.getSrcData(a);
                    this.addStackData(d.redoStack, c);
                    var e = d.undoStack.pop();
                    c === e && d.undoStack.length > 0 && (e = d.undoStack.pop());
                    e = b.util.toData(a, e);
                    d.wyswygMode ? b.util.innerHtml(d.iframeDoc.body, b.util.execSetHtmlHooks(a,
                        e)) : d.newTextarea.value = e
                }
            }, redo: function (a) {
                var d = b.g[a];
                if (d.redoStack.length != 0) {
                    var c = b.util.getSrcData(a);
                    this.addStackData(d.undoStack, c);
                    c = d.redoStack.pop();
                    c = b.util.toData(a, c);
                    d.wyswygMode ? b.util.innerHtml(d.iframeDoc.body, b.util.execSetHtmlHooks(a, c)) : d.newTextarea.value = c
                }
            }
        };
        b.readonly = function (a, d) {
            var d = d == c ? !0 : d, e = b.g[a];
            b.browser.IE ? e.iframeDoc.body.contentEditable = d ? "false" : "true" : e.iframeDoc.designMode = d ? "off" : "on"
        };
        b.focus = function (a, d) {
            d = (d || "").toLowerCase();
            if (b.g[a].container &&
                (b.util.focus(a), d === "end" && (b.util.setSelection(a), b.g[a].sel))) {
                var c = b.g[a].keSel, e = b.g[a].keRange;
                e.selectTextNode(b.g[a].iframeDoc.body);
                e.collapse(!1);
                c.addRange(e)
            }
        };
        b.blur = function (a) {
            a = b.g[a];
            if (a.container)if (b.browser.IE) {
                var d = b.$$("input");
                d.type = "text";
                a.container.appendChild(d);
                d.focus();
                a.container.removeChild(d)
            } else a.wyswygMode ? a.iframeWin.blur() : a.newTextarea.blur()
        };
        b.html = function (a, d) {
            if (d === c)return b.util.getData(a); else b.g[a].container && (b.util.setFullHtml(a, d), b.focus(a))
        };
        b.text = function (a, d) {
            if (d === c)return d = b.html(a), d = d.replace(/<.*?>/ig, ""), d = d.replace(/&nbsp;/ig, " "), d = b.util.trim(d); else b.html(a, b.util.escape(d))
        };
        b.insertHtml = function (a, d) {
            b.g[a].container && (b.g[a].range ? (b.focus(a), b.util.selection(a), b.util.insertHtml(a, d)) : b.appendHtml(a, d))
        };
        b.appendHtml = function (a, d) {
            b.html(a, b.html(a) + d)
        };
        b.isEmpty = function (a) {
            return b.util.isEmpty(a)
        };
        b.selectedHtml = function (a) {
            var d = b.g[a].range;
            if (!d)return "";
            var c = "";
            b.browser.IE ? c = d.item ? d.item(0).outerHTML : d.htmlText :
                (c = b.$$("div", b.g[a].iframeDoc), c.appendChild(d.cloneContents()), c = c.innerHTML);
            return b.util.toData(a, c)
        };
        b.count = function (a, c) {
            c = (c || "html").toLowerCase();
            if (c === "html")return b.html(a).length; else if (c === "text") {
                var e = b.util.getPureData(a), e = e.replace(/<(?:img|embed).*?>/ig, "K"), e = e.replace(/\r\n|\n|\r/g, ""), e = b.util.trim(e);
                return e.length
            }
            return 0
        };
        b.sync = function (a) {
            return b.util.setData(a)
        };
        b.remove = function (a, c) {
            var e = b.g[a];
            if (!e.container)return !1;
            c = typeof c == "undefined" ? 0 : c;
            b.util.setData(a);
            for (var k = e.container, h = e.eventStack, g = 0, i = h.length; g < i; g++) {
                var l = h[g];
                l && b.event.remove(l.el, l.type, l.fn, a)
            }
            e.iframeDoc.src = "javascript:false";
            e.iframe.parentNode.removeChild(e.iframe);
            if (c == 1)document.body.removeChild(k); else if (h = e.srcTextarea, h.parentNode.removeChild(k), c == 0)h.style.display = "";
            document.body.removeChild(e.hideDiv);
            document.body.removeChild(e.maskDiv);
            e.container = null;
            e.dialogStack = [];
            e.contextmenuItems = [];
            e.getHtmlHooks = [];
            e.setHtmlHooks = [];
            e.onchangeHandlerStack = [];
            e.eventStack =
                []
        };
        b.create = function (a, c) {
            function e() {
                b.hideMenu(a)
            }

            function k() {
                b.toolbar.updateState(a)
            }

            function h() {
                b.g[a].afterFocus && b.g[a].afterFocus(a)
            }

            function g() {
                b.g[a].afterBlur && b.g[a].afterBlur(a)
            }

            function i() {
                b.util.setSelection(a)
            }

            b.g[a].beforeCreate && b.g[a].beforeCreate(a);
            if (b.browser.IE && b.browser.VERSION < 7)try {
                document.execCommand("BackgroundImageCache", !1, !0)
            } catch (l) {
            }
            var j = b.$(a) || document.getElementsByName(a)[0], c = typeof c == "undefined" ? 0 : c;
            if (!(c == 0 && b.g[a].container)) {
                var m = b.g[a].width ||
                    j.style.width || j.offsetWidth + "px", o = b.g[a].height || j.style.height || j.offsetHeight + "px", n = b.util.createTable(), r = n.table;
                r.className = "ke-container";
                r.style.width = m;
                r.style.height = o;
                var s = n.cell;
                s.className = "ke-toolbar-outer";
                var q = r.insertRow(1).insertCell(0);
                q.className = "ke-textarea-outer";
                var n = b.util.createTable(), u = n.table;
                u.className = "ke-textarea-table";
                var w = n.cell;
                q.appendChild(u);
                var p = r.insertRow(2).insertCell(0);
                p.className = "ke-bottom-outer";
                j.style.display = "none";
                c == 1 ? document.body.appendChild(r) :
                    j.parentNode.insertBefore(r, j);
                q = b.toolbar.create(a);
                q.style.height = b.g[a].toolbarHeight + "px";
                s.appendChild(q);
                n = b.g[a].iframe || b.$$("iframe");
                n.tabIndex = b.g[a].tabIndex || j.tabIndex;
                n.className = "ke-iframe";
                n.setAttribute("frameBorder", "0");
                s = b.$$("textarea");
                s.tabIndex = n.tabIndex;
                s.className = "ke-textarea";
                s.style.display = "none";
                b.g[a].container = r;
                b.g[a].iframe = n;
                b.g[a].newTextarea = s;
                b.util.resize(a, m, o);
                w.appendChild(n);
                w.appendChild(s);
                w = b.$$("table");
                w.className = "ke-bottom";
                w.cellPadding = 0;
                w.cellSpacing =
                    0;
                w.border = 0;
                w.style.height = b.g[a].statusbarHeight + "px";
                var v = w.insertRow(0), t = v.insertCell(0);
                t.className = "ke-bottom-left";
                var x = b.$$("span");
                x.className = "ke-bottom-left-img";
                if (b.g[a].config.resizeMode == 0 || c == 1)t.style.cursor = "default", x.style.visibility = "hidden";
                t.appendChild(x);
                v = v.insertCell(1);
                v.className = "ke-bottom-right";
                x = b.$$("span");
                x.className = "ke-bottom-right-img";
                if (b.g[a].config.resizeMode == 0 || c == 1)v.style.cursor = "default", x.style.visibility = "hidden"; else if (b.g[a].config.resizeMode ==
                    1)v.style.cursor = "s-resize", x.style.visibility = "hidden";
                v.appendChild(x);
                p.appendChild(w);
                p = b.$$("div");
                p.className = "ke-reset";
                p.style.display = "none";
                x = b.$$("div");
                x.className = "ke-mask";
                b.util.setOpacity(x, 50);
                b.event.bind(x, "click", function () {
                }, a);
                b.event.bind(x, "mousedown", function () {
                }, a);
                document.body.appendChild(p);
                document.body.appendChild(x);
                b.util.setDefaultPlugin(a);
                var z = n.contentWindow, y = b.util.getIframeDoc(n);
                if (!b.browser.IE)y.designMode = "on";
                var A = b.util.getFullHtml(a);
                y.open();
                y.write(A);
                y.close();
                if (!b.g[a].wyswygMode)s.value = b.util.execSetHtmlHooks(a, j.value), s.style.display = "block", n.style.display = "none", b.toolbar.disable(a, ["source", "fullscreen"]), b.toolbar.select(a, "source");
                if (b.g[a].syncType == "form")for (n = j; n = n.parentNode;)if (n.nodeName.toLowerCase() == "form") {
                    b.event.add(n, "submit", function () {
                        b.sync(a)
                    }, a);
                    break
                }
                b.browser.WEBKIT && b.event.add(y, "click", function (c) {
                    b.util.selectImageWebkit(a, c, !0)
                }, a);
                b.browser.IE && b.event.add(y, "keydown", function (c) {
                    if (c.keyCode == 8 && (c = b.g[a].range,
                            c.item))return c = c.item(0), c.parentNode.removeChild(c), b.util.execOnchangeHandler(a), b.event.stop(a), !1
                }, a);
                b.event.add(y, "mousedown", e, a);
                b.event.add(y, "click", k, a);
                b.event.input(y, k, a);
                b.event.bind(s, "click", e, a);
                b.event.add(document, "click", e, a);
                b.event.add(z, "focus", h);
                b.event.add(s, "focus", h);
                b.event.add(z, "blur", g);
                b.event.add(s, "blur", g);
                b.g[a].toolbarTable = q;
                b.g[a].textareaTable = u;
                b.g[a].srcTextarea = j;
                b.g[a].bottom = w;
                b.g[a].hideDiv = p;
                b.g[a].maskDiv = x;
                b.g[a].iframeWin = z;
                b.g[a].iframeDoc = y;
                b.g[a].width = m;
                b.g[a].height = o;
                b.util.drag(a, v, r, function (c, d, e, f, g, h) {
                    b.g[a].resizeMode == 2 ? b.util.resize(a, e + h + "px", f + g + "px", !0) : b.g[a].resizeMode == 1 && b.util.resize(a, e + "px", f + g + "px", !0, !1)
                });
                b.util.drag(a, t, r, function (c, d, e, f, g) {
                    b.g[a].resizeMode > 0 && b.util.resize(a, e + "px", f + g + "px", !0, !1)
                });
                b.each(b.plugin, function (c, d) {
                    d.init && d.init(a)
                });
                b.g[a].getHtmlHooks.push(function (a) {
                    a = a.replace(/(<[^>]*)kesrc="([^"]+)"([^>]*>)/ig, function (a, c, d) {
                        a = a.replace(/(\s+(?:href|src)=")[^"]+(")/i, "$1" + d + "$2");
                        return a =
                            a.replace(/\s+kesrc="[^"]+"/i, "")
                    });
                    return a = a.replace(/(<[^>]+\s)ke-(on\w+="[^"]+"[^>]*>)/ig, function (a, c, d) {
                        return c + d
                    })
                });
                b.g[a].setHtmlHooks.push(function (a) {
                    a = a.replace(/(<[^>]*)(href|src)="([^"]+)"([^>]*>)/ig, function (a, c, d, b, e) {
                        if (a.match(/\skesrc="[^"]+"/i))return a;
                        return c + d + '="' + b + '" kesrc="' + b + '"' + e
                    });
                    return a = a.replace(/(<[^>]+\s)(on\w+="[^"]+"[^>]*>)/ig, function (a, c, d) {
                        return c + "ke-" + d
                    })
                });
                b.util.addContextmenuEvent(a);
                b.util.addNewlineEvent(a);
                b.util.addTabEvent(a);
                b.event.input(y,
                    i, a);
                b.event.add(y, "mouseup", i, a);
                b.event.add(document, "mousedown", i, a);
                b.onchange(a, function (a) {
                    if (b.g[a].autoSetDataMode || b.g[a].syncType == "auto")b.util.setData(a), b.g[a].afterSetData && b.g[a].afterSetData(a);
                    b.g[a].afterChange && b.g[a].afterChange(a);
                    b.history.add(a, b.g[a].minChangeSize)
                });
                if (b.browser.IE)y.body.disabled = !0, b.readonly(a, !1), y.body.removeAttribute("disabled");
                b.util.setFullHtml(a, j.value);
                b.history.add(a, 0);
                c > 0 && b.util.focus(a);
                b.g[a].afterCreate && b.g[a].afterCreate(a)
            }
        };
        b.onchange =
            function (a, c) {
                function e() {
                    c(a)
                }

                var k = b.g[a];
                k.onchangeHandlerStack.push(e);
                b.event.input(k.iframeDoc, e, a);
                b.event.input(k.newTextarea, e, a);
                b.event.add(k.iframeDoc, "mouseup", function () {
                    window.setTimeout(function () {
                        c(a)
                    }, 0)
                }, a)
            };
        var e = !0;
        b.init = function (a) {
            var c = b.g[a.id] = a;
            c.config = {};
            c.undoStack = [];
            c.redoStack = [];
            c.dialogStack = [];
            c.contextmenuItems = [];
            c.getHtmlHooks = [];
            c.setHtmlHooks = [];
            c.onchangeHandlerStack = [];
            c.eventStack = [];
            b.each(b.setting, function (b, e) {
                c[b] = typeof a[b] == "undefined" ? e : a[b];
                c.config[b] = c[b]
            });
            c.loadStyleMode && e && (b.util.loadStyle(c.skinsPath + c.skinType + ".css"), e = !1)
        };
        b.show = function (a) {
            b.init(a);
            b.event.ready(function () {
                b.create(a.id)
            })
        };
        if (window.KE === c)window.KE = b;
        window.KindEditor = b
    }
})();
(function (c) {
    c.langType = "zh_CN";
    c.lang = {
        source: "HTML\u4ee3\u7801",
        undo: "\u540e\u9000(Ctrl+Z)",
        redo: "\u524d\u8fdb(Ctrl+Y)",
        cut: "\u526a\u5207(Ctrl+X)",
        copy: "\u590d\u5236(Ctrl+C)",
        paste: "\u7c98\u8d34(Ctrl+V)",
        plainpaste: "\u7c98\u8d34\u4e3a\u65e0\u683c\u5f0f\u6587\u672c",
        wordpaste: "\u4eceWord\u7c98\u8d34",
        selectall: "\u5168\u9009",
        justifyleft: "\u5de6\u5bf9\u9f50",
        justifycenter: "\u5c45\u4e2d",
        justifyright: "\u53f3\u5bf9\u9f50",
        justifyfull: "\u4e24\u7aef\u5bf9\u9f50",
        insertorderedlist: "\u7f16\u53f7",
        insertunorderedlist: "\u9879\u76ee\u7b26\u53f7",
        indent: "\u589e\u52a0\u7f29\u8fdb",
        outdent: "\u51cf\u5c11\u7f29\u8fdb",
        subscript: "\u4e0b\u6807",
        superscript: "\u4e0a\u6807",
        title: "\u6807\u9898",
        fontname: "\u5b57\u4f53",
        fontsize: "\u6587\u5b57\u5927\u5c0f",
        textcolor: "\u6587\u5b57\u989c\u8272",
        bgcolor: "\u6587\u5b57\u80cc\u666f",
        bold: "\u7c97\u4f53(Ctrl+B)",
        italic: "\u659c\u4f53(Ctrl+I)",
        underline: "\u4e0b\u5212\u7ebf(Ctrl+U)",
        strikethrough: "\u5220\u9664\u7ebf",
        removeformat: "\u5220\u9664\u683c\u5f0f",
        image: "\u56fe\u7247",
        flash: "\u63d2\u5165Flash",
        media: "\u63d2\u5165\u591a\u5a92\u4f53",
        table: "\u63d2\u5165\u8868\u683c",
        hr: "\u63d2\u5165\u6a2a\u7ebf",
        emoticons: "\u63d2\u5165\u8868\u60c5",
        link: "\u8d85\u7ea7\u94fe\u63a5",
        unlink: "\u53d6\u6d88\u8d85\u7ea7\u94fe\u63a5",
        fullscreen: "\u5168\u5c4f\u663e\u793a",
        about: "\u5173\u4e8e",
        print: "\u6253\u5370",
        fileManager: "\u6d4f\u89c8\u670d\u52a1\u5668",
        advtable: "\u8868\u683c",
        tablecell: "\u5355\u5143\u683c",
        yes: "\u786e\u5b9a",
        no: "\u53d6\u6d88",
        close: "\u5173\u95ed",
        editImage: "\u56fe\u7247\u5c5e\u6027",
        deleteImage: "\u5220\u9664\u56fe\u7247",
        editLink: "\u8d85\u7ea7\u94fe\u63a5\u5c5e\u6027",
        deleteLink: "\u53d6\u6d88\u8d85\u7ea7\u94fe\u63a5",
        tableprop: "\u8868\u683c\u5c5e\u6027",
        tablecellprop: "\u5355\u5143\u683c\u5c5e\u6027",
        tableinsert: "\u63d2\u5165\u8868\u683c",
        tabledelete: "\u5220\u9664\u8868\u683c",
        tablecolinsertleft: "\u5de6\u4fa7\u63d2\u5165\u5217",
        tablecolinsertright: "\u53f3\u4fa7\u63d2\u5165\u5217",
        tablerowinsertabove: "\u4e0a\u65b9\u63d2\u5165\u884c",
        tablerowinsertbelow: "\u4e0b\u65b9\u63d2\u5165\u884c",
        tablecoldelete: "\u5220\u9664\u5217",
        tablerowdelete: "\u5220\u9664\u884c",
        noColor: "\u65e0\u989c\u8272",
        invalidImg: "\u8bf7\u8f93\u5165\u6709\u6548\u7684URL\u5730\u5740\u3002\n\u53ea\u5141\u8bb8jpg,gif,bmp,png\u683c\u5f0f\u3002",
        invalidMedia: "\u8bf7\u8f93\u5165\u6709\u6548\u7684URL\u5730\u5740\u3002\n\u53ea\u5141\u8bb8swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb\u683c\u5f0f\u3002",
        invalidWidth: "\u5bbd\u5ea6\u5fc5\u987b\u4e3a\u6570\u5b57\u3002",
        invalidHeight: "\u9ad8\u5ea6\u5fc5\u987b\u4e3a\u6570\u5b57\u3002",
        invalidBorder: "\u8fb9\u6846\u5fc5\u987b\u4e3a\u6570\u5b57\u3002",
        invalidUrl: "\u8bf7\u8f93\u5165\u6709\u6548\u7684URL\u5730\u5740\u3002",
        invalidRows: "\u884c\u6570\u4e3a\u5fc5\u9009\u9879\uff0c\u53ea\u5141\u8bb8\u8f93\u5165\u5927\u4e8e0\u7684\u6570\u5b57\u3002",
        invalidCols: "\u5217\u6570\u4e3a\u5fc5\u9009\u9879\uff0c\u53ea\u5141\u8bb8\u8f93\u5165\u5927\u4e8e0\u7684\u6570\u5b57\u3002",
        invalidPadding: "\u8fb9\u8ddd\u5fc5\u987b\u4e3a\u6570\u5b57\u3002",
        invalidSpacing: "\u95f4\u8ddd\u5fc5\u987b\u4e3a\u6570\u5b57\u3002",
        invalidBorder: "\u8fb9\u6846\u5fc5\u987b\u4e3a\u6570\u5b57\u3002",
        pleaseInput: "\u8bf7\u8f93\u5165\u5185\u5bb9\u3002",
        invalidJson: "\u670d\u52a1\u5668\u53d1\u751f\u6545\u969c\u3002",
        cutError: "\u60a8\u7684\u6d4f\u89c8\u5668\u5b89\u5168\u8bbe\u7f6e\u4e0d\u5141\u8bb8\u4f7f\u7528\u526a\u5207\u64cd\u4f5c\uff0c\u8bf7\u4f7f\u7528\u5feb\u6377\u952e(Ctrl+X)\u6765\u5b8c\u6210\u3002",
        copyError: "\u60a8\u7684\u6d4f\u89c8\u5668\u5b89\u5168\u8bbe\u7f6e\u4e0d\u5141\u8bb8\u4f7f\u7528\u590d\u5236\u64cd\u4f5c\uff0c\u8bf7\u4f7f\u7528\u5feb\u6377\u952e(Ctrl+C)\u6765\u5b8c\u6210\u3002",
        pasteError: "\u60a8\u7684\u6d4f\u89c8\u5668\u5b89\u5168\u8bbe\u7f6e\u4e0d\u5141\u8bb8\u4f7f\u7528\u7c98\u8d34\u64cd\u4f5c\uff0c\u8bf7\u4f7f\u7528\u5feb\u6377\u952e(Ctrl+V)\u6765\u5b8c\u6210\u3002"
    };
    var b = c.lang.plugins = {};
    b.about = {version: c.version, title: "HTML\u53ef\u89c6\u5316\u7f16\u8f91\u5668"};
    b.plainpaste = {comment: "\u8bf7\u4f7f\u7528\u5feb\u6377\u952e(Ctrl+V)\u628a\u5185\u5bb9\u7c98\u8d34\u5230\u4e0b\u9762\u7684\u65b9\u6846\u91cc\u3002"};
    b.wordpaste = {comment: "\u8bf7\u4f7f\u7528\u5feb\u6377\u952e(Ctrl+V)\u628a\u5185\u5bb9\u7c98\u8d34\u5230\u4e0b\u9762\u7684\u65b9\u6846\u91cc\u3002"};
    b.link = {
        url: "URL\u5730\u5740",
        linkType: "\u6253\u5f00\u7c7b\u578b",
        newWindow: "\u65b0\u7a97\u53e3",
        selfWindow: "\u5f53\u524d\u7a97\u53e3"
    };
    b.flash = {url: "Flash\u5730\u5740", width: "\u5bbd\u5ea6", height: "\u9ad8\u5ea6"};
    b.media = {
        url: "\u5a92\u4f53\u6587\u4ef6\u5730\u5740",
        width: "\u5bbd\u5ea6",
        height: "\u9ad8\u5ea6",
        autostart: "\u81ea\u52a8\u64ad\u653e"
    };
    b.image = {
        remoteImage: "\u8fdc\u7a0b\u56fe\u7247",
        localImage: "\u672c\u5730\u4e0a\u4f20",
        remoteUrl: "\u56fe\u7247\u5730\u5740",
        localUrl: "\u56fe\u7247\u5730\u5740",
        size: "\u56fe\u7247\u5927\u5c0f",
        width: "\u5bbd",
        height: "\u9ad8",
        resetSize: "\u91cd\u7f6e\u5927\u5c0f",
        align: "\u5bf9\u9f50\u65b9\u5f0f",
        defaultAlign: "\u9ed8\u8ba4\u65b9\u5f0f",
        leftAlign: "\u5de6\u5bf9\u9f50",
        rightAlign: "\u53f3\u5bf9\u9f50",
        imgTitle: "\u56fe\u7247\u8bf4\u660e",
        viewServer: "\u6d4f\u89c8..."
    };
    b.file_manager = {
        emptyFolder: "\u7a7a\u6587\u4ef6\u5939",
        moveup: "\u79fb\u5230\u4e0a\u4e00\u7ea7\u6587\u4ef6\u5939",
        viewType: "\u663e\u793a\u65b9\u5f0f\uff1a",
        viewImage: "\u7f29\u7565\u56fe",
        listImage: "\u8be6\u7ec6\u4fe1\u606f",
        orderType: "\u6392\u5e8f\u65b9\u5f0f\uff1a",
        fileName: "\u540d\u79f0",
        fileSize: "\u5927\u5c0f",
        fileType: "\u7c7b\u578b"
    };
    b.advtable = {
        cells: "\u5355\u5143\u683c\u6570",
        rows: "\u884c\u6570",
        cols: "\u5217\u6570",
        size: "\u5927\u5c0f",
        width: "\u5bbd\u5ea6",
        height: "\u9ad8\u5ea6",
        percent: "%",
        px: "px",
        space: "\u8fb9\u8ddd\u95f4\u8ddd",
        padding: "\u8fb9\u8ddd",
        spacing: "\u95f4\u8ddd",
        align: "\u5bf9\u9f50\u65b9\u5f0f",
        textAlign: "\u6c34\u5e73\u5bf9\u9f50",
        verticalAlign: "\u5782\u76f4\u5bf9\u9f50",
        alignDefault: "\u9ed8\u8ba4",
        alignLeft: "\u5de6\u5bf9\u9f50",
        alignCenter: "\u5c45\u4e2d",
        alignRight: "\u53f3\u5bf9\u9f50",
        alignTop: "\u9876\u90e8",
        alignMiddle: "\u4e2d\u90e8",
        alignBottom: "\u5e95\u90e8",
        alignBaseline: "\u57fa\u7ebf",
        border: "\u8fb9\u6846",
        borderWidth: "\u8fb9\u6846",
        borderColor: "\u989c\u8272",
        backgroundColor: "\u80cc\u666f\u989c\u8272"
    };
    b.title = {
        h1: "\u6807\u9898 1",
        h2: "\u6807\u9898 2",
        h3: "\u6807\u9898 3",
        h4: "\u6807\u9898 4",
        p: "\u6b63 \u6587"
    };
    b.fontname = {
        fontName: {
            SimSun: "\u5b8b\u4f53",
            NSimSun: "\u65b0\u5b8b\u4f53",
            FangSong_GB2312: "\u4eff\u5b8b_GB2312",
            KaiTi_GB2312: "\u6977\u4f53_GB2312",
            SimHei: "\u9ed1\u4f53",
            "Microsoft YaHei": "\u5fae\u8f6f\u96c5\u9ed1",
            Arial: "Arial",
            "Arial Black": "Arial Black",
            "Times New Roman": "Times New Roman",
            "Courier New": "Courier New",
            Tahoma: "Tahoma",
            Verdana: "Verdana"
        }
    }
})(KindEditor);
(function (c, b) {
    c.plugin.about = {
        click: function (b) {
            c.util.selection(b);
            (new c.dialog({
                id: b,
                cmd: "about",
                file: "about.html",
                width: 300,
                height: 70,
                loadingMode: !0,
                title: c.lang.about,
                noButton: c.lang.close
            })).show()
        }
    };
    c.plugin.undo = {
        init: function (b) {
            c.event.ctrl(c.g[b].iframeDoc, "Z", function () {
                c.plugin.undo.click(b);
                c.util.focus(b)
            }, b);
            c.event.ctrl(c.g[b].newTextarea, "Z", function () {
                c.plugin.undo.click(b);
                c.util.focus(b)
            }, b)
        }, click: function (b) {
            c.history.undo(b);
            c.util.execOnchangeHandler(b)
        }
    };
    c.plugin.redo = {
        init: function (b) {
            c.event.ctrl(c.g[b].iframeDoc,
                "Y", function () {
                    c.plugin.redo.click(b);
                    c.util.focus(b)
                }, b);
            c.event.ctrl(c.g[b].newTextarea, "Y", function () {
                c.plugin.redo.click(b);
                c.util.focus(b)
            }, b)
        }, click: function (b) {
            c.history.redo(b);
            c.util.execOnchangeHandler(b)
        }
    };
    c.plugin.cut = {
        click: function (b) {
            try {
                if (!c.g[b].iframeDoc.queryCommandSupported("cut"))throw"e";
            } catch (a) {
                alert(c.lang.cutError);
                return
            }
            c.util.execCommand(b, "cut", null)
        }
    };
    c.plugin.copy = {
        click: function (b) {
            try {
                if (!c.g[b].iframeDoc.queryCommandSupported("copy"))throw"e";
            } catch (a) {
                alert(c.lang.copyError);
                return
            }
            c.util.execCommand(b, "copy", null)
        }
    };
    c.plugin.paste = {
        click: function (b) {
            try {
                if (!c.g[b].iframeDoc.queryCommandSupported("paste"))throw"e";
            } catch (a) {
                alert(c.lang.pasteError);
                return
            }
            c.util.execCommand(b, "paste", null)
        }
    };
    c.plugin.plainpaste = {
        click: function (b) {
            c.util.selection(b);
            this.dialog = new c.dialog({
                id: b,
                cmd: "plainpaste",
                file: "plainpaste.html",
                width: 450,
                height: 300,
                loadingMode: !0,
                title: c.lang.plainpaste,
                yesButton: c.lang.yes,
                noButton: c.lang.no
            });
            this.dialog.show()
        }, exec: function (b) {
            var a = c.util.getIframeDoc(this.dialog.iframe),
                a = c.$("textArea", a).value, a = c.util.escape(a), a = a.replace(/ /g, "&nbsp;"), a = c.g[b].newlineTag == "p" ? a.replace(/^/, "<p>").replace(/$/, "</p>").replace(/\r\n|\n|\r/g, "</p><p>") : a.replace(/\r\n|\n|\r/g, "<br />$&");
            c.util.insertHtml(b, a);
            this.dialog.hide();
            c.util.focus(b)
        }
    };
    c.plugin.wordpaste = {
        click: function (b) {
            c.util.selection(b);
            this.dialog = new c.dialog({
                id: b,
                cmd: "wordpaste",
                file: "wordpaste.html",
                width: 450,
                height: 300,
                loadingMode: !0,
                title: c.lang.wordpaste,
                yesButton: c.lang.yes,
                noButton: c.lang.no
            });
            this.dialog.show()
        },
        exec: function (b) {
            var a = c.util.getIframeDoc(this.dialog.iframe), a = c.$("wordIframe", a), a = c.util.getIframeDoc(a).body.innerHTML, a = a.replace(/<meta(\n|.)*?>/ig, ""), a = a.replace(/<!(\n|.)*?>/ig, ""), a = a.replace(/<style[^>]*>(\n|.)*?<\/style>/ig, ""), a = a.replace(/<script[^>]*>(\n|.)*?<\/script>/ig, ""), a = a.replace(/<w:[^>]+>(\n|.)*?<\/w:[^>]+>/ig, ""), a = a.replace(/<xml>(\n|.)*?<\/xml>/ig, ""), a = a.replace(/\r\n|\n|\r/ig, ""), a = c.util.execGetHtmlHooks(b, a), a = c.format.getHtml(a, c.g[b].htmlTags, c.g[b].urlType);
            c.util.insertHtml(b,
                a);
            this.dialog.hide();
            c.util.focus(b)
        }
    };
    c.plugin.fullscreen = {
        click: function (b) {
            var a = c.g[b], d = this, f = function () {
                var b = c.util.getDocumentElement();
                a.width = b.clientWidth + "px";
                a.height = b.clientHeight + "px"
            }, k = "", h = function () {
                if (d.isSelected) {
                    var g = c.util.getDocumentElement(), g = [g.clientWidth, g.clientHeight].join("");
                    k != g && (k = g, f(), c.util.resize(b, a.width, a.height))
                }
            };
            if (this.isSelected)this.isSelected = !1, c.util.setData(b), c.remove(b, 1), a.width = this.width, a.height = this.height, c.create(b, 2), document.body.parentNode.style.overflow =
                "auto", c.event.remove(window, "resize", h), a.resizeMode = a.config.resizeMode, c.toolbar.unselect(b, "fullscreen"); else {
                this.isSelected = !0;
                this.width = a.container.style.width;
                this.height = a.container.style.height;
                c.util.setData(b);
                c.remove(b, 2);
                document.body.parentNode.style.overflow = "hidden";
                f();
                c.create(b, 1);
                var g = c.util.getScrollPos(), i = a.container;
                i.style.position = "absolute";
                i.style.left = g.x + "px";
                i.style.top = g.y + "px";
                i.style.zIndex = 19811211;
                c.event.add(window, "resize", h);
                a.resizeMode = 0;
                c.toolbar.select(b,
                    "fullscreen")
            }
        }
    };
    c.plugin.bgcolor = {
        click: function (b) {
            c.util.selection(b);
            var a = c.queryCommandValue(c.g[b].iframeDoc, "bgcolor");
            this.menu = new c.menu({id: b, cmd: "bgcolor"});
            this.menu.picker(a)
        }, exec: function (b, a) {
            var d = new c.cmd(b);
            a == "" ? d.remove({span: [".background-color"]}) : d.wrap("span", [{".background-color": a}]);
            c.util.execOnchangeHandler(b);
            this.menu.hide();
            c.util.focus(b)
        }
    };
    c.plugin.fontname = {
        click: function (b) {
            var a = c.lang.plugins.fontname.fontName;
            c.util.selection(b);
            var d = new c.menu({
                id: b, cmd: "fontname",
                width: 150
            }), f = c.queryCommandValue(c.g[b].iframeDoc, "fontname");
            c.each(a, function (a, h) {
                d.add('<span class="ke-reset" style="font-family: ' + a + ';">' + h + "</span>", function () {
                    c.plugin.fontname.exec(b, a)
                }, {checked: f === a.toLowerCase() || f === h.toLowerCase()})
            });
            d.show();
            this.menu = d
        }, exec: function (b, a) {
            (new c.cmd(b)).wrap("span", [{".font-family": a}]);
            c.util.execOnchangeHandler(b);
            this.menu.hide();
            c.util.focus(b)
        }
    };
    c.plugin.fontsize = {
        click: function (b) {
            var a = ["9px", "10px", "12px", "14px", "16px", "18px", "24px", "32px"];
            c.util.selection(b);
            for (var d = c.queryCommandValue(c.g[b].iframeDoc, "fontsize"), f = new c.menu({
                id: b,
                cmd: "fontsize",
                width: 120
            }), k = 0, h = a.length; k < h; k++) {
                var g = a[k];
                f.add('<span class="ke-reset" style="font-size: ' + g + ';">' + g + "</span>", function (a) {
                    return function () {
                        c.plugin.fontsize.exec(b, a)
                    }
                }(g), {height: parseInt(g) + 12 + "px", checked: d === g})
            }
            f.show();
            this.menu = f
        }, exec: function (b, a) {
            (new c.cmd(b)).wrap("span", [{".font-size": a}]);
            c.util.execOnchangeHandler(b);
            this.menu.hide();
            c.util.focus(b)
        }
    };
    c.plugin.hr =
    {
        click: function (b) {
            c.util.selection(b);
            c.util.insertHtml(b, "<hr />");
            c.util.focus(b)
        }
    };
    c.plugin.print = {
        click: function (b) {
            c.util.selection(b);
            c.g[b].iframeWin.print()
        }
    };
    c.plugin.removeformat = {
        click: function (b) {
            c.util.selection(b);
            for (var a = new c.cmd(b), d = {"*": ["class", "style"]}, f = 0, k = c.g[b].inlineTags.length; f < k; f++)d[c.g[b].inlineTags[f]] = ["*"];
            a.remove(d);
            c.util.execOnchangeHandler(b);
            c.toolbar.updateState(b);
            c.util.focus(b)
        }
    };
    c.plugin.source = {
        click: function (b) {
            var a = c.g[b];
            a.wyswygMode ? (c.hideMenu(b),
                a.newTextarea.value = c.util.getData(b), a.iframe.style.display = "none", a.newTextarea.style.display = "block", c.toolbar.disable(b, ["source", "fullscreen"]), a.wyswygMode = !1, this.isSelected = !0, c.toolbar.select(b, "source")) : (c.util.setFullHtml(b, a.newTextarea.value), a.iframe.style.display = "block", a.newTextarea.style.display = "none", c.toolbar.able(b, ["source", "fullscreen"]), a.wyswygMode = !0, this.isSelected = !1, c.toolbar.unselect(b, "source"));
            c.util.focus(b)
        }
    };
    c.plugin.textcolor = {
        click: function (b) {
            c.util.selection(b);
            var a = c.queryCommandValue(c.g[b].iframeDoc, "textcolor");
            this.menu = new c.menu({id: b, cmd: "textcolor"});
            this.menu.picker(a)
        }, exec: function (b, a) {
            var d = new c.cmd(b);
            a == "" ? d.remove({span: [".color"], font: ["color"]}) : d.wrap("span", [{".color": a}]);
            c.util.execOnchangeHandler(b);
            this.menu.hide();
            c.util.focus(b)
        }
    };
    c.plugin.title = {
        click: function (b) {
            var a = c.lang.plugins.title, a = {H1: a.h1, H2: a.h2, H3: a.h3, H4: a.h4, P: a.p}, d = {
                H1: 28,
                H2: 24,
                H3: 18,
                H4: 14,
                P: 12
            };
            c.util.selection(b);
            var f = c.queryCommandValue(c.g[b].iframeDoc,
                "formatblock"), k = new c.menu({id: b, cmd: "title", width: c.langType == "en" ? 200 : 150});
            c.each(a, function (a, g) {
                var i = "font-size:" + d[a] + "px;";
                a !== "P" && (i += "font-weight:bold;");
                k.add('<span class="ke-reset" style="' + i + '">' + g + "</span>", function () {
                    c.plugin.title.exec(b, "<" + a + ">")
                }, {height: d[a] + 12 + "px", checked: f === a.toLowerCase() || f === g.toLowerCase()})
            });
            k.show();
            this.menu = k
        }, exec: function (b, a) {
            c.util.select(b);
            c.util.execCommand(b, "formatblock", a);
            this.menu.hide();
            c.util.focus(b)
        }
    };
    c.plugin.emoticons = {
        click: function (e) {
            function a(a) {
                var b =
                    c.$$("table");
                if (r)b.onmouseover = function () {
                    r.style.display = "block"
                }, b.onmouseout = function () {
                    r.style.display = "none"
                };
                b.className = "ke-plugin-emoticons-table";
                b.cellPadding = 0;
                b.cellSpacing = 0;
                b.border = 0;
                for (var a = (a - 1) * i + g, d = 0; d < k; d++)for (var l = b.insertRow(d), m = 0; m < h; m++) {
                    var n = l.insertCell(m);
                    n.className = "ke-plugin-emoticons-cell";
                    n.onmouseover = r ? function (a, b) {
                        return function () {
                            a > j ? (r.style.left = 0, r.style.right = "") : (r.style.left = "", r.style.right = 0);
                            s.src = o + b + ".gif";
                            this.className = "ke-plugin-emoticons-cell ke-plugin-emoticons-cell-on"
                        }
                    }(m,
                        a) : function () {
                        this.className = "ke-plugin-emoticons-cell ke-plugin-emoticons-cell-on"
                    };
                    n.onmouseout = function () {
                        this.className = "ke-plugin-emoticons-cell"
                    };
                    n.onclick = function (a) {
                        return function () {
                            f.exec(e, a);
                            return !1
                        }
                    }(a);
                    var q = c.$$("span");
                    q.className = "ke-plugin-emoticons-img";
                    q.style.backgroundPosition = "-" + 24 * a + "px 0px";
                    n.appendChild(q);
                    a++
                }
                return b
            }

            function d(b) {
                for (var e = 1; e <= l; e++) {
                    if (b !== e) {
                        var f = c.$$("a");
                        f.href = "javascript:;";
                        f.innerHTML = "[" + e + "]";
                        f.onclick = function (b) {
                            return function () {
                                n.removeChild(q);
                                var c = a(b);
                                n.insertBefore(c, u);
                                q = c;
                                u.innerHTML = "";
                                d(b);
                                return !1
                            }
                        }(e);
                        u.appendChild(f)
                    } else u.appendChild(document.createTextNode("[" + e + "]"));
                    u.appendChild(document.createTextNode(" "))
                }
            }

            var f = this, k = 5, h = 9, g = 0, i = k * h, l = Math.ceil(135 / i), j = Math.floor(h / 2), m = c.g[e], o = m.pluginsPath + "emoticons/", m = m.allowPreviewEmoticons === b ? !0 : m.allowPreviewEmoticons;
            c.util.selection(e);
            var n = c.$$("div");
            n.className = "ke-plugin-emoticons-wrapper";
            var r, s;
            if (m)r = c.$$("div"), r.className = "ke-plugin-emoticons-preview", r.style.right =
                0, s = c.$$("img"), s.className = "ke-reset", s.src = o + "0.gif", s.border = 0, r.appendChild(s), n.appendChild(r);
            var q = a(1);
            n.appendChild(q);
            var u = c.$$("div");
            u.className = "ke-plugin-emoticons-page";
            n.appendChild(u);
            d(1);
            m = new c.menu({id: e, cmd: "emoticons"});
            m.append(n);
            m.show();
            this.menu = m
        }, exec: function (b, a) {
            var d = c.g[b].pluginsPath + "emoticons/" + a + ".gif";
            c.util.insertHtml(b, '<img src="' + d + '" kesrc="' + d + '" alt="" />');
            this.menu.hide();
            c.util.focus(b)
        }
    };
    c.plugin.flash = {
        init: function (b) {
            c.g[b].getHtmlHooks.push(function (a) {
                return a.replace(/<img[^>]*class="?ke-flash"?[^>]*>/ig,
                    function (a) {
                        var b = a.match(/style="[^"]*;?\s*width:\s*(\d+)/i) ? RegExp.$1 : 0, e = a.match(/style="[^"]*;?\s*height:\s*(\d+)/i) ? RegExp.$1 : 0, b = b || (a.match(/width="([^"]+)"/i) ? RegExp.$1 : 0), e = e || (a.match(/height="([^"]+)"/i) ? RegExp.$1 : 0);
                        if (a.match(/kesrctag="([^"]+)"/i))return a = c.util.getAttrList(unescape(RegExp.$1)), a.width = b || a.width || 0, a.height = e || a.height || 0, a.kesrc = a.src, c.util.getMediaEmbed(a)
                    })
            });
            c.g[b].setHtmlHooks.push(function (a) {
                return a.replace(/<embed[^>]*type="application\/x-shockwave-flash"[^>]*>(?:<\/embed>)?/ig,
                    function (a) {
                        var f = a.match(/\s+src="([^"]+)"/i) ? RegExp.$1 : "";
                        if (a.match(/\s+kesrc="([^"]+)"/i))f = RegExp.$1;
                        var k = a.match(/\s+width="([^"]+)"/i) ? RegExp.$1 : 0, h = a.match(/\s+height="([^"]+)"/i) ? RegExp.$1 : 0, a = c.util.getAttrList(a);
                        a.src = f;
                        a.width = k;
                        a.height = h;
                        return c.util.getMediaImage(b, "flash", a)
                    })
            })
        }, click: function (b) {
            c.util.selection(b);
            this.dialog = new c.dialog({
                id: b,
                cmd: "flash",
                file: "flash.html",
                width: 400,
                height: 140,
                loadingMode: !0,
                title: c.lang.flash,
                yesButton: c.lang.yes,
                noButton: c.lang.no
            });
            this.dialog.show()
        },
        check: function (b, a, d, f) {
            b = c.util.getIframeDoc(this.dialog.iframe);
            if (!a.match(/^.{3,}$/))return alert(c.lang.invalidUrl), c.$("url", b).focus(), !1;
            if (!d.match(/^\d*$/))return alert(c.lang.invalidWidth), c.$("width", b).focus(), !1;
            if (!f.match(/^\d*$/))return alert(c.lang.invalidHeight), c.$("height", b).focus(), !1;
            return !0
        }, exec: function (b) {
            var a = c.util.getIframeDoc(this.dialog.iframe), d = c.$("url", a).value, f = c.$("width", a).value, a = c.$("height", a).value;
            if (!this.check(b, d, f, a))return !1;
            d = c.util.getMediaImage(b,
                "flash", {src: d, type: c.g[b].mediaTypes.flash, width: f, height: a, quality: "high"});
            c.util.insertHtml(b, d);
            this.dialog.hide();
            c.util.focus(b)
        }
    };
    c.plugin.image = {
        getSelectedNode: function (b) {
            var b = c.g[b], a = b.keRange.startNode, d = b.keRange.endNode;
            if ((c.browser.WEBKIT || b.keSel.isControl) && a.nodeType == 1 && a.tagName.toLowerCase() == "img" && a == d && !a.className.match(/^ke-\w+/i))return a
        }, init: function (b) {
            var a = this, b = c.g[b];
            b.contextmenuItems.push({
                text: c.lang.editImage,
                click: function (b, e) {
                    c.util.select(b);
                    e.hide();
                    a.click(b)
                },
                cond: function (b) {
                    return a.getSelectedNode(b)
                },
                options: {
                    width: "150px",
                    iconHtml: '<span class="ke-common-icon ke-common-icon-url ke-icon-image"></span>'
                }
            });
            b.contextmenuItems.push({
                text: c.lang.deleteImage, click: function (b, e) {
                    c.util.select(b);
                    e.hide();
                    var k = a.getSelectedNode(b);
                    k.parentNode.removeChild(k);
                    c.util.execOnchangeHandler(b)
                }, cond: function (b) {
                    return a.getSelectedNode(b)
                }, options: {width: "150px"}
            });
            b.contextmenuItems.push("-")
        }, click: function (b) {
            c.util.selection(b);
            this.dialog = new c.dialog({
                id: b,
                cmd: "image",
                file: "image/image.html",
                width: 400,
                height: 220,
                loadingMode: !0,
                title: c.lang.image,
                yesButton: c.lang.yes,
                noButton: c.lang.no
            });
            this.dialog.show()
        }, check: function () {
            var b = c.util.getIframeDoc(this.dialog.iframe), a = c.$("type", b).value, d = c.$("imgWidth", b).value, f = c.$("imgHeight", b).value;
            c.$("imgTitle", b);
            a = a == 2 ? c.$("imgFile", b) : c.$("url", b);
            if (!a.value.match(/\.(jpg|jpeg|gif|bmp|png)(\s|\?|$)/i))return alert(c.lang.invalidImg), a.focus(), !1;
            if (!d.match(/^\d*$/))return alert(c.lang.invalidWidth),
                c.$("imgWidth", b).focus(), !1;
            if (!f.match(/^\d*$/))return alert(c.lang.invalidHeight), c.$("imgHeight", b).focus(), !1;
            return !0
        }, exec: function (b) {
            for (var a = this, d = c.util.getIframeDoc(this.dialog.iframe), f = c.$("type", d).value, k = c.$("imgWidth", d).value, h = c.$("imgHeight", d).value, g = c.$("imgTitle", d).value, i = d.getElementsByName("align"), l = "", j = 0, m = i.length; j < m; j++)if (i[j].checked) {
                l = i[j].value;
                break
            }
            if (!this.check(b))return !1;
            if (f == 2) {
                c.$("editorId", d).value = b;
                var o = c.$("uploadIframe", d);
                c.util.showLoadingPage(b);
                var n = function () {
                    c.event.remove(o, "load", n);
                    c.util.hideLoadingPage(b);
                    var d = c.util.getIframeDoc(o), f = "";
                    try {
                        f = c.util.parseJson(d.body.innerHTML)
                    } catch (i) {
                        alert(c.lang.invalidJson)
                    }
                    if (typeof f === "object" && "error"in f)if (f.error === 0)d = c.format.getUrl(f.url, "absolute"), a.insert(b, d, g, k, h, 0, l); else return alert(f.message), !1
                };
                c.event.add(o, "load", n);
                d.uploadForm.submit()
            } else d = c.$("url", d).value, this.insert(b, d, g, k, h, 0, l)
        }, insert: function (b, a, d, f, k, h, g) {
            a = '<img src="' + a + '" kesrc="' + a + '" ';
            f > 0 && (a += 'width="' +
            f + '" ');
            k > 0 && (a += 'height="' + k + '" ');
            d && (a += 'title="' + d + '" ');
            g && (a += 'align="' + g + '" ');
            a += 'alt="' + d + '" ';
            h && (a += 'border="' + h + '" ');
            a += "/>";
            c.util.insertHtml(b, a);
            this.dialog.hide();
            c.util.focus(b)
        }
    };
    c.plugin.link = {
        getSelectedNode: function (b) {
            return c.getCommonAncestor(c.g[b].keSel, "a")
        }, init: function (b) {
            var a = this;
            c.g[b].contextmenuItems.push({
                text: c.lang.editLink,
                click: function (b, e) {
                    c.util.select(b);
                    e.hide();
                    a.click(b)
                },
                cond: function (b) {
                    return a.getSelectedNode(b)
                },
                options: {
                    width: "150px",
                    iconHtml: '<span class="ke-common-icon ke-common-icon-url ke-icon-link"></span>'
                }
            })
        },
        click: function (b) {
            c.util.selection(b);
            this.dialog = new c.dialog({
                id: b,
                cmd: "link",
                file: "link/link.html",
                width: 400,
                height: 90,
                loadingMode: !0,
                title: c.lang.link,
                yesButton: c.lang.yes,
                noButton: c.lang.no
            });
            this.dialog.show()
        }, exec: function (b) {
            var a = c.g[b];
            c.util.select(b);
            var d = a.keRange, f = d.startNode, k = d.endNode, h = a.iframeDoc, g = c.util.getIframeDoc(this.dialog.iframe), i = c.$("hyperLink", g).value, l = c.$("linkType", g).value;
            if (!i.match(/.+/) || i.match(/^\w+:\/\/\/?$/))return alert(c.lang.invalidUrl), c.$("hyperLink",
                g).focus(), !1;
            for (g = d.getParentElement(); g;) {
                if (g.tagName.toLowerCase() == "a" || g.tagName.toLowerCase() == "body")break;
                g = g.parentNode
            }
            var g = g.parentNode, k = c.browser.IE ? !!a.range.item : f.nodeType == 1 && f === k && f.nodeName.toLowerCase() != "br", j = !k;
            k || (j = c.browser.IE ? a.range.text === "" : a.range.toString() === "");
            if (j || c.util.isEmpty(b))a = '<a href="' + i + '"', l && (a += ' target="' + l + '"'), a += ">" + i + "</a>", c.util.insertHtml(b, a); else {
                h.execCommand("createlink", !1, "__ke_temp_url__");
                for (var g = g.getElementsByTagName("a"),
                         j = 0, m = g.length; j < m; j++)if (g[j].href.match(/\/?__ke_temp_url__$/))g[j].href = i, g[j].setAttribute("kesrc", i), l ? g[j].target = l : g[j].removeAttribute("target");
                if (c.browser.WEBKIT && k && f.tagName.toLowerCase() == "img")k = f.parentNode, k.tagName.toLowerCase() != "a" && (h = c.$$("a", h), k.insertBefore(h, f), h.appendChild(f), k = h), k.href = i, k.setAttribute("kesrc", i), l ? k.target = l : k.removeAttribute("target"), a.keSel.addRange(d)
            }
            c.util.execOnchangeHandler(b);
            this.dialog.hide();
            c.util.focus(b)
        }
    };
    c.plugin.unlink = {
        init: function (b) {
            var a =
                this;
            c.g[b].contextmenuItems.push({
                text: c.lang.deleteLink,
                click: function (b, e) {
                    c.util.select(b);
                    e.hide();
                    a.click(b)
                },
                cond: function (a) {
                    return c.plugin.link.getSelectedNode(a)
                },
                options: {
                    width: "150px",
                    iconHtml: '<span class="ke-common-icon ke-common-icon-url ke-icon-unlink"></span>'
                }
            });
            c.g[b].contextmenuItems.push("-")
        }, click: function (b) {
            var a = c.g[b], d = a.iframeDoc;
            c.util.selection(b);
            var f = a.keRange, k = f.startNode, f = f.endNode, f = k.nodeType == 1 && k === f, h = !f;
            f || (h = c.browser.IE ? a.range.text === "" : a.range.toString() ===
            "");
            if (h) {
                h = c.plugin.link.getSelectedNode(b);
                if (!h)return;
                f = a.keRange;
                f.selectTextNode(h);
                a.keSel.addRange(f);
                c.util.select(b);
                d.execCommand("unlink", !1, null);
                if (c.browser.WEBKIT && k.tagName.toLowerCase() == "img")d = k.parentNode, d.tagName.toLowerCase() == "a" && (c.util.removeParent(d), a.keSel.addRange(f))
            } else d.execCommand("unlink", !1, null);
            c.util.execOnchangeHandler(b);
            c.toolbar.updateState(b);
            c.util.focus(b)
        }
    };
    c.plugin.media = {
        init: function (b) {
            var a = {};
            c.each(c.g[b].mediaTypes, function (b, c) {
                a[c] = b
            });
            c.g[b].getHtmlHooks.push(function (a) {
                return a.replace(/<img[^>]*class="?ke-\w+"?[^>]*>/ig, function (a) {
                    var b = a.match(/style="[^"]*;?\s*width:\s*(\d+)/i) ? RegExp.$1 : 0, d = a.match(/style="[^"]*;?\s*height:\s*(\d+)/i) ? RegExp.$1 : 0, b = b || (a.match(/width="([^"]+)"/i) ? RegExp.$1 : 0), d = d || (a.match(/height="([^"]+)"/i) ? RegExp.$1 : 0);
                    if (a.match(/\s+kesrctag="([^"]+)"/i))return a = c.util.getAttrList(unescape(RegExp.$1)), a.width = b || a.width || 0, a.height = d || a.height || 0, a.kesrc = a.src, c.util.getMediaEmbed(a)
                })
            });
            c.g[b].setHtmlHooks.push(function (d) {
                return d.replace(/<embed[^>]*type="([^"]+)"[^>]*>(?:<\/embed>)?/ig,
                    function (d, k) {
                        if (typeof a[k] == "undefined")return d;
                        var h = d.match(/\s+src="([^"]+)"/i) ? RegExp.$1 : "";
                        if (d.match(/\s+kesrc="([^"]+)"/i))h = RegExp.$1;
                        var g = d.match(/\s+width="([^"]+)"/i) ? RegExp.$1 : 0, i = d.match(/\s+height="([^"]+)"/i) ? RegExp.$1 : 0, l = c.util.getAttrList(d);
                        l.src = h;
                        l.width = g;
                        l.height = i;
                        return c.util.getMediaImage(b, "", l)
                    })
            })
        }, click: function (b) {
            c.util.selection(b);
            this.dialog = new c.dialog({
                id: b,
                cmd: "media",
                file: "media.html",
                width: 400,
                height: 170,
                loadingMode: !0,
                title: c.lang.media,
                yesButton: c.lang.yes,
                noButton: c.lang.no
            });
            this.dialog.show()
        }, check: function (b, a, d, f) {
            b = c.util.getIframeDoc(this.dialog.iframe);
            if (!a.match(/^.{3,}\.(swf|flv|mp3|wav|wma|wmv|mid|avi|mpg|mpeg|asf|rm|rmvb)(\?|$)/i))return alert(c.lang.invalidMedia), c.$("url", b).focus(), !1;
            if (!d.match(/^\d*$/))return alert(c.lang.invalidWidth), c.$("width", b).focus(), !1;
            if (!f.match(/^\d*$/))return alert(c.lang.invalidHeight), c.$("height", b).focus(), !1;
            return !0
        }, exec: function (b) {
            var a = c.util.getIframeDoc(this.dialog.iframe), d = c.$("url", a).value,
                f = c.$("width", a).value, k = c.$("height", a).value;
            if (!this.check(b, d, f, k))return !1;
            a = c.$("autostart", a).checked ? "true" : "false";
            d = c.util.getMediaImage(b, "", {
                src: d,
                type: c.g[b].mediaTypes[c.util.getMediaType(d)],
                width: f,
                height: k,
                autostart: a,
                loop: "true"
            });
            c.util.insertHtml(b, d);
            this.dialog.hide();
            c.util.focus(b)
        }
    };
    c.plugin.advtable = {
        getSelectedTable: function (b) {
            return c.getCommonAncestor(c.g[b].keSel, "table")
        }, getSelectedRow: function (b) {
            return c.getCommonAncestor(c.g[b].keSel, "tr")
        }, getSelectedCell: function (b) {
            return c.getCommonAncestor(c.g[b].keSel,
                "td")
        }, tableprop: function (b) {
            this.click(b)
        }, tablecellprop: function (b) {
            var a = this;
            c.util.selection(b);
            var d = new c.dialog({
                id: b,
                cmd: "advtable",
                file: "advtable/cell.html",
                width: 420,
                height: 150,
                loadingMode: !0,
                title: c.lang.tablecell,
                yesButton: c.lang.yes,
                noButton: c.lang.no,
                yesClickFn: function (b) {
                    var e = c.util.getIframeDoc(d.iframe), h = c.$("width", e), g = c.$("height", e), i = c.$("widthType", e), l = c.$("heightType", e), j = c.$("textAlign", e), m = c.$("verticalAlign", e), o = c.$("border", e), n = c.$("borderColor", e), r = c.$("backgroundColor",
                        e), e = h.value, s = g.value, i = i.value, l = l.value, j = j.value, m = m.value, q = o.value, n = n.innerHTML, r = r.innerHTML;
                    if (!e.match(/^\d*$/))return alert(c.lang.invalidWidth), h.focus(), !1;
                    if (!s.match(/^\d*$/))return alert(c.lang.invalidHeight), g.focus(), !1;
                    if (!q.match(/^\d*$/))return alert(c.lang.invalidBorder), o.focus(), !1;
                    h = a.getSelectedCell(b);
                    h.style.width = e !== "" ? e + i : "";
                    h.style.height = s !== "" ? s + l : "";
                    h.style.backgroundColor = r;
                    h.style.textAlign = j;
                    h.style.verticalAlign = m;
                    h.style.borderWidth = q;
                    h.style.borderStyle = q !==
                    "" ? "solid" : "";
                    h.style.borderColor = n;
                    c.util.execOnchangeHandler(b);
                    d.hide();
                    c.util.focus(b)
                }
            });
            d.show()
        }, tableinsert: function (b) {
            this.click(b, "insert")
        }, tabledelete: function (b) {
            b = this.getSelectedTable(b);
            b.parentNode.removeChild(b)
        }, tablecolinsert: function (b, a) {
            for (var c = this.getSelectedTable(b), f = this.getSelectedCell(b).cellIndex + a, k = 0, h = c.rows.length; k < h; k++)c.rows[k].insertCell(f).innerHTML = "&nbsp;"
        }, tablecolinsertleft: function (b) {
            this.tablecolinsert(b, 0)
        }, tablecolinsertright: function (b) {
            this.tablecolinsert(b,
                1)
        }, tablerowinsert: function (b, a) {
            for (var c = this.getSelectedTable(b), f = this.getSelectedRow(b), c = c.insertRow(f.rowIndex + a), k = 0, f = f.cells.length; k < f; k++)c.insertCell(k).innerHTML = "&nbsp;"
        }, tablerowinsertabove: function (b) {
            this.tablerowinsert(b, 0)
        }, tablerowinsertbelow: function (b) {
            this.tablerowinsert(b, 1)
        }, tablecoldelete: function (b) {
            for (var a = this.getSelectedTable(b), b = this.getSelectedCell(b).cellIndex, c = 0, f = a.rows.length; c < f; c++)a.rows[c].deleteCell(b)
        }, tablerowdelete: function (b) {
            var a = this.getSelectedTable(b),
                b = this.getSelectedRow(b);
            a.deleteRow(b.rowIndex)
        }, init: function (e) {
            for (var a = this, d = "prop,cellprop,colinsertleft,colinsertright,rowinsertabove,rowinsertbelow,coldelete,rowdelete,insert,delete".split(","), f = 0, k = d.length; f < k; f++) {
                var h = "table" + d[f];
                c.g[e].contextmenuItems.push({
                    text: c.lang[h],
                    click: function (d) {
                        return function (e, f) {
                            c.util.select(e);
                            f.hide();
                            if (a[d] !== b)a[d](e);
                            /prop/.test(d) || c.util.execOnchangeHandler(e)
                        }
                    }(h),
                    cond: function (b) {
                        return c.util.inArray(b, ["tableprop", "tabledelete"]) ? function (b) {
                            return a.getSelectedTable(b)
                        } :
                            function (b) {
                                return a.getSelectedCell(b)
                            }
                    }(h),
                    options: {
                        width: "170px",
                        iconHtml: '<span class="ke-common-icon ke-common-icon-url ke-icon-' + h + '"></span>'
                    }
                })
            }
            c.g[e].contextmenuItems.push("-");
            c.g[e].setHtmlHooks.push(function (a) {
                return a.replace(/<table([^>]*)>/ig, function (a, b) {
                    if (b.match(/\s+border=["']?(\d*)["']?/ig)) {
                        var d = RegExp.$1;
                        return b.indexOf("ke-zeroborder") < 0 && (d === "" || d === "0") ? c.addClass(a, "ke-zeroborder") : a
                    } else return c.addClass(a, "ke-zeroborder")
                })
            })
        }, click: function (b, a) {
            a = a || "default";
            c.util.selection(b);
            this.dialog = new c.dialog({
                id: b,
                cmd: "advtable",
                file: "advtable/advtable.html?mode=" + a,
                width: 420,
                height: 220,
                loadingMode: !0,
                title: c.lang.advtable,
                yesButton: c.lang.yes,
                noButton: c.lang.no
            });
            this.dialog.show()
        }, exec: function (b) {
            var a = c.util.getIframeDoc(this.dialog.iframe), d = c.$("mode", a), f = c.$("rows", a), k = c.$("cols", a), h = c.$("width", a), g = c.$("height", a), i = c.$("widthType", a), l = c.$("heightType", a), j = c.$("padding", a), m = c.$("spacing", a), o = c.$("align", a), n = c.$("border", a), r = c.$("borderColor",
                a), s = c.$("backgroundColor", a), a = f.value, q = k.value, u = h.value, w = g.value, i = i.value, p = l.value, l = j.value, v = m.value, o = o.value, t = n.value, r = r.innerHTML, s = s.innerHTML;
            if (a == "" || a == 0 || !a.match(/^\d*$/))return alert(c.lang.invalidRows), f.focus(), !1;
            if (q == "" || q == 0 || !q.match(/^\d*$/))return alert(c.lang.invalidCols), k.focus(), !1;
            if (!u.match(/^\d*$/))return alert(c.lang.invalidWidth), h.focus(), !1;
            if (!w.match(/^\d*$/))return alert(c.lang.invalidHeight), g.focus(), !1;
            if (!l.match(/^\d*$/))return alert(c.lang.invalidPadding),
                j.focus(), !1;
            if (!v.match(/^\d*$/))return alert(c.lang.invalidSpacing), m.focus(), !1;
            if (!t.match(/^\d*$/))return alert(c.lang.invalidBorder), n.focus(), !1;
            if (d.value === "update")a = this.getSelectedTable(b), a.style.width = u !== "" ? u + i : "", a.style.height = w !== "" ? w + p : "", a.style.backgroundColor = s, c.attr(a, "width", ""), c.attr(a, "height", ""), c.attr(a, "bgColor", ""), c.attr(a, "cellPadding", l), c.attr(a, "cellSpacing", v), c.attr(a, "align", o), t === "" || t === "0" ? c.addClass(a, "ke-zeroborder") : c.removeClass(a, "ke-zeroborder"),
                c.attr(a, "border", t), c.attr(a, "borderColor", r), c.util.execOnchangeHandler(b); else {
                d = "";
                u !== "" && (d += "width:" + u + i + ";");
                w !== "" && (d += "height:" + w + p + ";");
                s !== "" && (d += "background-color:" + s + ";");
                u = "<table";
                d !== "" && (u += ' style="' + d + '"');
                l !== "" && (u += ' cellpadding="' + l + '"');
                v !== "" && (u += ' cellspacing="' + v + '"');
                o !== "" && (u += ' align="' + o + '"');
                if (t === "" || t === "0")u += ' class="ke-zeroborder"';
                t !== "" && (u += ' border="' + t + '"');
                r !== "" && (u += ' bordercolor="' + r + '"');
                u += ">";
                for (w = 0; w < a; w++) {
                    u += "<tr>";
                    for (d = 0; d < q; d++)u += "<td>&nbsp;</td>";
                    u += "</tr>"
                }
                u += "</table>";
                c.util.insertHtml(b, u)
            }
            this.dialog.hide();
            c.util.focus(b)
        }
    }
})(KindEditor);

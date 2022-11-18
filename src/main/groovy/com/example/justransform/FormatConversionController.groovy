package com.example.justransform

import groovy.json.JsonBuilder
import groovy.xml.XmlParser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FormatConversionController {

    /**
     *
     * @param content - string from GET Request send to /convert endpoint
     * @return HttpResponse object {"status": (200 or 400), "body": (JSON string or error message)}
     */
    @GetMapping("/convert")
    HttpResponse convertToJson(@RequestBody String content) {
        /**
         * Try to parse the request as XML
         * if success: convert XML to JSON and return
         * if fail: return Status 400
         */
        try {
            def rootNode = new XmlParser().parseText(content)
            def jsonObject = build(rootNode)
            def json = new JsonBuilder(jsonObject)
            return new HttpResponse(200, json.toString())
        } catch (Exception _) {
            return new HttpResponse(400, content + " is not a valid XML expression")
        }
    }

    /**
     * Helper method to build plain old java object from xml node
     * @param node - XML Node Type
     * @return map - LinkedHashMap<String, Object>
     */
    def build(node){
        // Initialize a map with the root node
        def map = ['name': node.name()]

        // Add attributes to the map
        if (!node.attributes().isEmpty()) {
            map.put('attributes', node.attributes().collectEntries{it})
        }

        // Add all of the root node's children to the map
        if (!node.children().isEmpty() && !(node.children().get(0) instanceof String)) {
            map.put('children', node.children().collect{build(it)}.findAll{it != null})
        } else if (node.text() != ''){
            map.put('value', node.text())
        }
        map
    }


}

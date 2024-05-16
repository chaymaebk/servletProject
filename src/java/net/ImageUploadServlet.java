/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
/**
 *
 * @author TechAima
 */
@WebServlet(name = "ImageUploadServlet", urlPatterns = {"/uploadImage"})
public class ImageUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the image file from the request
        Part filePart = request.getPart("image");
        InputStream fileContent = filePart.getInputStream();
        
        // Convert the file to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        byte[] image = baos.toByteArray();
        
        // Convert the byte array to a Base64-encoded string
        String base64Image = Base64.getEncoder().encodeToString(image);
        
        // Now you can use the base64Image string as needed (e.g., store it in your database)
        
        // Don't forget to close resources
        fileContent.close();
        baos.close();
    }
}
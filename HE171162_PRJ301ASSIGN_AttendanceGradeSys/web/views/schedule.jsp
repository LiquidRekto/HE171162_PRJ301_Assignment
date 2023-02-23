<%-- 
    Document   : schedule.jsp
    Created on : Feb 19, 2023, 6:34:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
    <link rel="stylesheet" href="../css/dist/index.css" />
  </head>
  <body>
    <div class="fixed z-50 w-full bg-orange-400 p-4 text-left">
      <p class="text-xl font-bold text-white">FPT Academic Portal</p>
    </div>

    <div class="py-10"></div>
    <h1 class="my-10 text-center text-3xl font-bold">Weekly Schedule</h1>
    <div class="w-full py-10 text-center">
      <div class="inline-block px-2">
        <label> Instructor: </label>
        <input class="border-box rounded-lg border border-gray-500 bg-gray-200 p-2" type="text" placeholder="Type here..." />
      </div>
      <div class="inline-block px-2">
        <button class="border-box rounded-lg bg-blue-700 px-6 py-3 font-bold text-white hover:bg-blue-600">View</button>
      </div>
    </div>
    <div class="relative my-0 mx-auto w-5/6 mb-12 overflow-x-auto text-center shadow-md sm:rounded-xl">
      <table class="w-full">
        <thead class="bg-blue-600 text-xs uppercase text-white">
          <tr>
            <th class="px-6 py-2" rowspan="2">Hello</th>
            <th class="px-6 pt-4" scope="col">Mon</th>
            <th class="px-6 pt-4" scope="col">Tue</th>
            <th class="px-6 pt-4" scope="col">Wed</th>
            <th class="px-6 pt-4" scope="col">Thu</th>
            <th class="px-6 pt-4" scope="col">Fri</th>
            <th class="px-6 pt-4" scope="col">Sat</th>
            <th class="px-6 pt-4" scope="col">Sun</th>
          </tr>
          <tr>
            <th class="px-6 pb-4 text-base" scope="col">(6/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(7/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(8/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(9/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(10/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(11/2)</th>
            <th class="px-6 pb-4 text-base" scope="col">(12/2)</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 1</td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-green-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">IOT1702</label>
                <label class="text-base font-bold text-white">PRJ301</label>
                <label class="text-xs italic text-white">at BE-301</label>
              </div>
            </td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-green-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">IOT1702</label>
                <label class="text-base font-bold text-white">PRJ301</label>
                <label class="text-xs italic text-white">at BE-301</label>
              </div>
            </td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-cyan-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">IOT1702</label>
                <label class="text-base font-bold text-white">PRJ301</label>
                <label class="text-xs italic text-white">at BE-301</label>
              </div>
            </td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 2</td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-red-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">AI1604</label>
                <label class="text-base font-bold text-white">DBI202</label>
                <label class="text-xs italic text-white">at BE-315</label>
              </div>
            </td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-green-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">AI1604</label>
                <label class="text-base font-bold text-white">DBI202</label>
                <label class="text-xs italic text-white">at BE-315</label>
              </div>
            </td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-cyan-500 px-6 py-3">
                <label class="text-xs font-bold text-gray-300">SE1501-NET</label>
                <label class="text-base font-bold text-white">PRU211m</label>
                <label class="text-xs italic text-white">at BE-331</label>
              </div>
            </td>
            <td class="h-4 w-6 text-base">
              <div class="h-full w-full bg-cyan-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">AI1604</label>
                <label class="text-base font-bold text-white">DBI202</label>
                <label class="text-xs italic text-white">at BE-315</label>
              </div>
            </td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 3</td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-green-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">SE1610</label>
                <label class="text-base font-bold text-white">PRJ301</label>
                <label class="text-xs italic text-white">at DE-217</label>
              </div>
            </td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-green-500 px-6 py-3">
                <label class="text-xs font-bold text-gray-300">SE1501-NET</label>
                <label class="text-base font-bold text-white">PRU211m</label>
                <label class="text-xs italic text-white">at BE-331</label>
              </div>
            </td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-green-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">SE1610</label>
                <label class="text-base font-bold text-white">PRJ301</label>
                <label class="text-xs italic text-white">at DE-217</label>
              </div>
            </td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-cyan-500 px-6 py-3">
                <label class="text-xs font-bold text-gray-300">SE1501-NET</label>
                <label class="text-base font-bold text-white">PRU211m</label>
                <label class="text-xs italic text-white">at BE-331</label>
              </div>
            </td>
            <td class="h-3 w-6 text-base">
              <div class="h-full w-full bg-cyan-500 px-6 py-3">
                <label class="text-sm font-bold text-gray-300">SE1610</label>
                <label class="text-base font-bold text-white">PRJ301</label>
                <label class="text-xs italic text-white">at DE-217</label>
              </div>
            </td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 4</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 5</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 6</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 7</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 8</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
          <tr>
            <td class="px-6 py-3 text-base" scope="row">Slot 9</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
            <td class="px-6 py-3 text-base">-</td>
          </tr>
        </tbody>
      </table>
    </div>
  </body>
</html>

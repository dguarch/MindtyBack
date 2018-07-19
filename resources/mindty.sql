-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 19-07-2018 a las 01:50:42
-- Versión del servidor: 5.6.20-log
-- Versión de PHP: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mindty`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE IF NOT EXISTS `curso` (
`idCurso` int(11) NOT NULL,
  `codCurso` varchar(255) DEFAULT NULL,
  `horasCurso` int(11) DEFAULT NULL,
  `nombreCurso` varchar(255) DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`idCurso`, `codCurso`, `horasCurso`, `nombreCurso`) VALUES
(1, 'WEB 65823', 50, 'Desarrollo de aplicaciones con tecnologías web'),
(2, 'UXP 12578', 255, 'Introducción a SQL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_alumno`
--

CREATE TABLE IF NOT EXISTS `curso_alumno` (
  `idCurso` int(11) NOT NULL,
  `idAlumno` int(11) NOT NULL,
  `idu` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_formador`
--

CREATE TABLE IF NOT EXISTS `curso_formador` (
  `idFormador` int(11) DEFAULT NULL,
  `idCurso` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `curso_formador`
--

INSERT INTO `curso_formador` (`idFormador`, `idCurso`) VALUES
(5, 1),
(6, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_modulo`
--

CREATE TABLE IF NOT EXISTS `curso_modulo` (
  `idCurso` int(11) NOT NULL,
  `idModulo` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulo`
--

CREATE TABLE IF NOT EXISTS `modulo` (
`idm` int(11) NOT NULL,
  `nombreModulo` varchar(255) DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `modulo`
--

INSERT INTO `modulo` (`idm`, `nombreModulo`) VALUES
(1, 'Preparación previa'),
(2, 'Implementación de aplicaciones web'),
(3, 'Desarrollo avanzado'),
(4, 'Conceptos básicos de SQL'),
(5, 'Consultas de SQL más avanzadas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
`idu` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombreUsuario` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idu`, `email`, `nombreUsuario`, `password`, `telefono`, `tipo`) VALUES
(1, 'ht@ht.com', 'Héctor Torres', 'Password', '93-555-75-54', 3),
(2, 'is@is.com', 'Ivan Synytsya', 'Password', '93-555-75-54', 3),
(3, 'ya@ya.com', 'Youssef Ait', 'Password', '93-555-75-54', 3),
(4, 'dg@dg.com', 'David Guarch', 'Password', '93-555-75-54', 3),
(5, 'ra@ra.com', 'Ricardo Ahumada', 'Password', '93-555-75-55', 2),
(6, 'am@am.com', 'Ángel M. Rayo', 'Password', '93-555-75-55', 2),
(7, 'sp@sp.com', 'Silvia Perez', 'Password', '93-555-75-55', 2),
(8, 'al@al.com', 'Aina Llorens', 'Password', '93-555-75-56', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
 ADD PRIMARY KEY (`idCurso`), ADD UNIQUE KEY `UK_rsqdt06md7614u6qmr74esx6c` (`codCurso`);

--
-- Indices de la tabla `curso_alumno`
--
ALTER TABLE `curso_alumno`
 ADD PRIMARY KEY (`idCurso`,`idu`), ADD KEY `FK6eoshliykiitlexla1v7er7c3` (`idAlumno`);

--
-- Indices de la tabla `curso_formador`
--
ALTER TABLE `curso_formador`
 ADD PRIMARY KEY (`idCurso`), ADD KEY `FKqv81k45p38rssra9mowdaa0cn` (`idFormador`);

--
-- Indices de la tabla `curso_modulo`
--
ALTER TABLE `curso_modulo`
 ADD KEY `FKfhi847kmbjqsk3h8dby4ea4d2` (`idModulo`), ADD KEY `FKc2juqnyphc5s7ifpjp5ipfn6d` (`idCurso`);

--
-- Indices de la tabla `modulo`
--
ALTER TABLE `modulo`
 ADD PRIMARY KEY (`idm`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
 ADD PRIMARY KEY (`idu`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `curso`
--
ALTER TABLE `curso`
MODIFY `idCurso` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `modulo`
--
ALTER TABLE `modulo`
MODIFY `idm` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
MODIFY `idu` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
